package com.leaf.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.leaf.dto.Result;
import com.leaf.dto.ShopDoc;
import com.leaf.entity.Shop;
import com.leaf.mapper.ShopMapper;
import com.leaf.service.IShopService;
import com.leaf.utils.CacheClient;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.leaf.utils.MqConstants.*;
import static com.leaf.utils.RedisConstants.CACHE_SHOP_KEY;
import static com.leaf.utils.RedisConstants.CACHE_SHOP_TTL;
import static com.leaf.utils.SystemConstants.DEFAULT_PAGE_SIZE;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RestHighLevelClient client;

    @Resource
    private CacheClient cacheClient;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public Result queryById(Long id) {
        // 解决缓存穿透
        Shop shop = cacheClient
                .queryWithPassThrough(CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);

        // 互斥锁解决缓存击穿
        // Shop shop = cacheClient
        //         .queryWithMutex(CACHE_SHOP_KEY, id, Shop.class, this::getById, CACHE_SHOP_TTL, TimeUnit.MINUTES);

        // 逻辑过期解决缓存击穿
        // Shop shop = cacheClient
        //         .queryWithLogicalExpire(CACHE_SHOP_KEY, id, Shop.class, this::getById, 20L, TimeUnit.SECONDS);

        if (shop == null) {
            return Result.fail("店铺不存在！");
        }
        // 7.返回
        return Result.ok(shop);
    }

    @Override
    @Transactional
    public Result update(Shop shop) {
        Long id = shop.getId();
        if (id == null) {
            return Result.fail("店铺id不能为空");
        }
        // 1.更新数据库
        updateById(shop);
        // 2.删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + id);
        //3.数据同步给es
        rabbitTemplate.convertAndSend(SHOP_EXCHANGE, SHOP_INSERT_KEY, id);
        return Result.ok();
    }

    @Override
    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y) {
        try {
            int start = (current - 1) * DEFAULT_PAGE_SIZE;
            SearchRequest searchRequest = new SearchRequest("shop");
            searchRequest.source().query(QueryBuilders.matchQuery("typeId",typeId)).from(start).size(DEFAULT_PAGE_SIZE);
            // 1.判断是否需要根据坐标查询
            if (x != null && y != null) {
                searchRequest.source().sort(SortBuilders
                        .geoDistanceSort("location", new GeoPoint(y+","+x))
                        .order(SortOrder.ASC)
                        .unit(DistanceUnit.METERS));
            }
            SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
            return Result.ok(handleResponse(response));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertOrUpdateBy(Long id) {
        try {
            //根据id查数据
            Shop shop = getById(id);
            //转换为文档数据
            ShopDoc shopDoc=new ShopDoc(shop);
            //准备request对象
            IndexRequest request = new IndexRequest("shop").id(shop.getId().toString());
            //准备json文档
            request.source(JSON.toJSONString(shopDoc), XContentType.JSON);
            //发送请求
            client.index(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            DeleteRequest request = new DeleteRequest("shop", id.toString());
            client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Result deleteShopById(Long id) {
        removeById(id);
        rabbitTemplate.convertAndSend(SHOP_EXCHANGE, SHOP_DELETE_KEY, id);
        return Result.ok();
    }

    @Override
    public Result saveShop(Shop shop) {
        save(shop);
        rabbitTemplate.convertAndSend(SHOP_EXCHANGE, SHOP_INSERT_KEY, shop.getId());
        return Result.ok();
    }

    private List<ShopDoc> handleResponse(SearchResponse response) {
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        SearchHit[] searchHits = hits.getHits();
        List<ShopDoc> shopDocList = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            String json = searchHit.getSourceAsString();
            ShopDoc shopDoc = JSON.parseObject(json, ShopDoc.class);
            Object[] sortValues = searchHit.getSortValues();
            if(sortValues.length > 0) {
                shopDoc.setDistance(sortValues[0]);
            }
            shopDocList.add(shopDoc);

        }
        return shopDocList;
    }

//    @Override
//    public Result queryShopByType(Integer typeId, Integer current, Double x, Double y) {
//        // 1.判断是否需要根据坐标查询
//        if (x == null || y == null) {
//            // 不需要坐标查询，按数据库查询
//            Page<Shop> page = query()
//                    .eq("type_id", typeId)
//                    .page(new Page<>(current, SystemConstants.DEFAULT_PAGE_SIZE));
//            // 返回数据
//            return Result.ok(page.getRecords());
//        }
//
//        // 2.计算分页参数
//        int from = (current - 1) * SystemConstants.DEFAULT_PAGE_SIZE;
//        int end = current * SystemConstants.DEFAULT_PAGE_SIZE;
//
//        // 3.查询redis、按照距离排序、分页。结果：shopId、distance
//        String key = SHOP_GEO_KEY + typeId;
//        GeoResults<RedisGeoCommands.GeoLocation<String>> results = stringRedisTemplate.opsForGeo() // GEOSEARCH key BYLONLAT x y BYRADIUS 10 WITHDISTANCE
//                .search(
//                        key,
//                        GeoReference.fromCoordinate(x, y),
//                        new Distance(5000),
//                        RedisGeoCommands.GeoSearchCommandArgs.newGeoSearchArgs().includeDistance().limit(end)
//                );
//        // 4.解析出id
//        if (results == null) {
//            return Result.ok(Collections.emptyList());
//        }
//        List<GeoResult<RedisGeoCommands.GeoLocation<String>>> list = results.getContent();
//        if (list.size() <= from) {
//            // 没有下一页了，结束
//            return Result.ok(Collections.emptyList());
//        }
//        // 4.1.截取 from ~ end的部分
//        List<Long> ids = new ArrayList<>(list.size());
//        Map<String, Distance> distanceMap = new HashMap<>(list.size());
//        list.stream().skip(from).forEach(result -> {
//            // 4.2.获取店铺id
//            String shopIdStr = result.getContent().getName();
//            ids.add(Long.valueOf(shopIdStr));
//            // 4.3.获取距离
//            Distance distance = result.getDistance();
//            distanceMap.put(shopIdStr, distance);
//        });
//        // 5.根据id查询Shop
//        String idStr = StrUtil.join(",", ids);
//        List<Shop> shops = query().in("id", ids).last("ORDER BY FIELD(id," + idStr + ")").list();
//        for (Shop shop : shops) {
//            shop.setDistance(distanceMap.get(shop.getId().toString()).getValue());
//        }
//        // 6.返回
//        return Result.ok(shops);
//    }

}
