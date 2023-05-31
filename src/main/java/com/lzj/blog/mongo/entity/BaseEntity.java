package com.lzj.blog.mongo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@Setter
@ToString
public class BaseEntity {
    @MongoId(FieldType.OBJECT_ID)
    protected String id;
    @Field("创建时间")
    @CreatedDate
    protected Date createTime;
    @Field("更新时间")
    @LastModifiedDate
    protected Date updateTime;
}
