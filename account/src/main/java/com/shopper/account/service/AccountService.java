package com.shopper.account.service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.shopper.account.exceptions.InvalidPasswordException;
import com.shopper.account.exceptions.UserNotFoundException;
import com.shopper.account.model.LoginRequestBody;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final MongoClient mongoClient;
    private final MongoOperations mongoOperations;

    @Autowired
    AccountService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        this.mongoOperations = new MongoTemplate(mongoClient, "login_db");
    }

    public String login(final LoginRequestBody loginRequestBody) {
        FindIterable<Document> result = mongoOperations.getCollection("userDetails")
                .find(Filters.eq("userName", loginRequestBody.getUserName()), Document.class);
        MongoCursor<Document> resultItr = result.iterator();

        if(!resultItr.hasNext()) {
            throw new UserNotFoundException("User {0} not found", loginRequestBody.getUserName());
        }

        Document userDetail = resultItr.next();
        if(loginRequestBody.getPassword().equals(userDetail.get("password"))) {
            return "Login Successful";
        }

        throw new InvalidPasswordException();
    }

}
