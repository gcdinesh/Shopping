package com.shopper.account.db;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.shopper.account.exceptions.MongoInsertionException;
import com.shopper.account.model.DBDAO;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;

public abstract class AbstractMongoDbOperations {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMongoDbOperations.class);
    protected MongoOperations mongoOperations;

    AbstractMongoDbOperations() {
    }

    public <T extends DBDAO> void insert(T DAO, String collectionName) {
        try {
            mongoOperations.insert(DAO, collectionName);
        } catch (Exception e) {
            LOGGER.error("Exception while inserting record in mongodb", e);
            throw new MongoInsertionException();
        }
    }

    public MongoCursor<Document> find(Bson filter, String collectionName) {
        FindIterable<Document> result = mongoOperations.getCollection(collectionName)
                .find(filter, Document.class);
        return result.iterator();
    }
}
