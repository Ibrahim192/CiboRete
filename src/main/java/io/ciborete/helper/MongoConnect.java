package io.ciborete.helper;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.*;

@Configuration
public class MongoConnect {

    public @Bean
    MongoOperations mongoTemplate(){
        MongoDbFactory mongo = mongo();
        return new MongoTemplate(mongo);
    }

    public @Bean
    MongoDbFactory mongo(){
        MongoDbFactory dbFactory = new SimpleMongoDbFactory(new MongoClient("localhost"),"ciborete");
        return dbFactory;
    }
}
