package com.leaf.service;

import com.leaf.dto.Result;
import com.leaf.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
public interface IShopService extends IService<Shop> {

    Result queryById(Long id);

    Result update(Shop shop);

    Result queryShopByType(Integer typeId, Integer current, Double x, Double y);

    void insertOrUpdateBy(Long id);

    void deleteById(Long id);

    Result saveShop(Shop shop);

    Result deleteShopById(Long id);
}
