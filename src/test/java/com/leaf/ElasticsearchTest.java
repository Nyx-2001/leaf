package com.leaf;

import com.alibaba.fastjson.JSON;
import com.leaf.dto.ShopDoc;
import com.leaf.entity.Shop;
import com.leaf.service.IShopService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static com.leaf.utils.ElasticsearchConstants.SHOP_MAPPING_TEMPLATE;

/**
 * @Author Hanami
 * @Date 2023-03-01
 */
@Slf4j
@SpringBootTest
class ElasticsearchTest {
    @Resource
    private RestHighLevelClient client;

    @Resource
    private IShopService shopService;

    @Test
    void createShopIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest("shop");
        request.source(SHOP_MAPPING_TEMPLATE, XContentType.JSON);
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    @Test
    void bulkRequest() throws IOException {
        BulkRequest request = new BulkRequest();
        List<Shop> shops = shopService.list();
        for (Shop shop : shops) {
            ShopDoc shopDoc = new ShopDoc(shop);
            request.add(new IndexRequest("shop")
                    .id(shopDoc.getId().toString()).source(JSON.toJSONString(shopDoc),XContentType.JSON));
        }
        client.bulk(request,RequestOptions.DEFAULT);

    }

    @Test
    void addDocument() throws IOException {
        Shop shop = shopService.getById(2L);
        ShopDoc shopDoc=new ShopDoc(shop);
        IndexRequest request=new IndexRequest("shop").id(shopDoc.getId().toString());
        request.source(JSON.toJSONString(shopDoc),XContentType.JSON);
        client.index(request,RequestOptions.DEFAULT);
    }

    @Test
    void searchByType () throws IOException {
        int start = 0;
        double x = 120.00;
        double y = 30.00;
        SearchRequest searchRequest = new SearchRequest("shop");
        searchRequest.source().query(QueryBuilders.matchQuery("typeId",1));
        // 1.判断是否需要根据坐标查询
//            searchRequest.source().sort(SortBuilders
//                    .geoDistanceSort("location", new GeoPoint(y+","+x))
//                    .order(SortOrder.ASC)
//                    .unit(DistanceUnit.KILOMETERS));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        handleResponse(response);
    }


    private void handleResponse(SearchResponse response) {
        SearchHits hits = response.getHits();
        long total = hits.getTotalHits().value;
        SearchHit[] searchHits = hits.getHits();
//        List<ShopDoc> shopDocList = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            String json = searchHit.getSourceAsString();
            ShopDoc shopDoc = JSON.parseObject(json, ShopDoc.class);
//            Object[] sortValues = searchHit.getSortValues();
//            if(sortValues.length > 0) {
//                shopDoc.setDistance(sortValues[0]);
//            }
//            shopDocList.add(shopDoc);
            System.out.println(shopDoc.toString());
        }
    }
}
