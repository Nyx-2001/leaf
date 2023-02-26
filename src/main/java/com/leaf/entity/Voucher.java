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
@TableName("voucher")
@ApiModel(value="Voucher对象", description="")
public class Voucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商铺id")
    private Long shopId;

    @ApiModelProperty(value = "代金券标题")
    private String title;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "使用规则")
    private String rules;

    @ApiModelProperty(value = "支付金额，单位是分。例如200代表2元")
    private Long payValue;

    @ApiModelProperty(value = "抵扣金额，单位是分。例如200代表2元")
    private Long actualValue;

    @ApiModelProperty(value = "0,普通券；1,秒杀券")
    private Integer type;

    @ApiModelProperty(value = "1,上架; 2,下架; 3,过期")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "库存")
    @TableField(exist = false)
    private Integer stock;

    @ApiModelProperty(value = "生效时间")
    @TableField(exist = false)
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "失效时间")
    @TableField(exist = false)
    private LocalDateTime endTime;

}
