package com.leaf.dto;

import com.leaf.entity.Shop;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Hanami
 * @Date 2023-02-28
 */
@Data
@NoArgsConstructor
public class ShopDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "商铺名称")
    private String name;

    @ApiModelProperty(value = "商铺类型的id")
    private Long typeId;

    @ApiModelProperty(value = "商铺图片，多个图片以','隔开")
    private String images;

    @ApiModelProperty(value = "商圈，例如陆家嘴")
    private String area;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "均价，取整数")
    private Long avgPrice;

    @ApiModelProperty(value = "销量")
    private Integer sold;

    @ApiModelProperty(value = "评论数量")
    private Integer comments;

    @ApiModelProperty(value = "评分，1~5分，乘10保存，避免小数")
    private Integer score;

    @ApiModelProperty(value = "营业时间，例如 10:00-22:00")
    private String openHours;

    @ApiModelProperty(value = "经度")
    private Double x;

    @ApiModelProperty(value = "维度")
    private Double y;


    @ApiModelProperty(value = "位置")
    private String location;

    @ApiModelProperty(value = "距离")
    private Object distance;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    public ShopDoc(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
        this.typeId = shop.getTypeId();
        this.images = shop.getImages();
        this.area = shop.getArea();
        this.address = shop.getAddress();
        this.avgPrice = shop.getAvgPrice();
        this.sold = shop.getSold();
        this.comments = shop.getComments();
        this.score = shop.getScore();
        this.createTime = shop.getCreateTime();
        this.updateTime = shop.getUpdateTime();
        this.openHours = shop.getOpenHours();
        this.x = shop.getX();
        this.y = shop.getY();
        this.location = shop.getY()+","+shop.getX();
    }
}
