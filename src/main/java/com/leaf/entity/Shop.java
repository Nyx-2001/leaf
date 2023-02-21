package com.leaf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop")
@ApiModel(value="Shop对象", description="")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
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

    @ApiModelProperty(value = "经度")
    private Double x;

    @ApiModelProperty(value = "维度")
    private Double y;

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

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private Double distance;

}
