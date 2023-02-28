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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hanami
 * @since 2023-02-21
 */
@Data
@Document(collection = "blogcomment")
@ApiModel(value="BlogComments对象", description="blog评论")
public class BlogComments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @Id
    private String id;

    @ApiModelProperty(value = "用户id")
    @Indexed
    @Field("userid")
    private String userid;

    @ApiModelProperty(value = "笔记id")
    @Field("blogid")
    private String blogid;

    @ApiModelProperty(value = "关联的1级评论id，如果是一级评论，则值为0")
    @Field("parentid")
    private String parentid;

    @ApiModelProperty(value = "回复的评论id")
    @Field("answerid")
    private String answerid;

    @ApiModelProperty(value = "回复的内容")
    @Field("content")
    private String content;

    @ApiModelProperty(value = "点赞数")
    private Integer liked;

    @ApiModelProperty(value = "状态，0：正常，1：被举报，2：禁止查看")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @Field("createtime")
    @CreatedDate
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @Field("updatetime")
    @LastModifiedDate
    private LocalDateTime updateTime;

}
