package com.reactive.spring.config;

import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

import com.mongodb.reactivestreams.client.MongoClient;

@Configuration
@Import(EmbeddedMongoAutoConfiguration.class)
public class DataConfig {
	 public static final String DATABASE_NAME = "reservation";
	@Bean
	public ReactiveMongoDatabaseFactory mongoDataFactory(MongoClient mongoClient) {
		return new SimpleReactiveMongoDatabaseFactory(mongoClient,DATABASE_NAME);
		
	}
	@Bean
	public ReactiveMongoOperations reactiveMongoOperations(ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory) {
		 return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory);
	}

}
