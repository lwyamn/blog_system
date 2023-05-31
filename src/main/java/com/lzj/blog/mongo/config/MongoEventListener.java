package com.lzj.blog.mongo.config;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoEventListener extends AbstractMongoEventListener<Object> {
    private final MongoTemplate mongoTemplate;

    public MongoEventListener(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

}
