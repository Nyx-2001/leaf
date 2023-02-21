package com.leaf.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 秒杀优惠券表，与优惠券是一对一关系
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("seckill_voucher")
@ApiModel(value="SeckillVoucher对象", description="秒杀优惠券表，与优惠券是一对一关系")
public class SeckillVoucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关联的优惠券的id")
    @TableId(value = "voucher_id", type = IdType.AUTO)
    private Long voucherId;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "生效时间")
    private LocalDateTime beginTime;

    @ApiModelProperty(value = "失效时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
