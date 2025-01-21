package com.project.rate_my_college.service;

import com.project.rate_my_college.model.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    // This method generates the next ID for any given collection
    public long getNextSequenceId(String collectionName) {
        Query query = new Query(Criteria.where("_id").is(collectionName));
        Update update = new Update().inc("seq", 1);  // Increment the sequence by 1
        Counter counter = mongoOperations.findAndModify(
                query,
                update,
                FindAndModifyOptions.options().returnNew(true).upsert(true),  // Upsert creates if not exists
                Counter.class
        );

        return counter != null ? counter.getSeq() : 1;  // Default to 1 if null
    }
}
