package com.shopper.account.db;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginDB extends AbstractMongoDbOperations {
    public static final String USER_DETAILS_COLLECTION = "userDetails";

    @Autowired
    public LoginDB(MongoClient mongoClient) {
        this.mongoOperations = new MongoTemplate(mongoClient, "login_db");
    }
}
