package ru.gpsbox.test.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.Entity.KeySeq;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class KeySeqRepo   {
    @Autowired
    private MongoTemplate mongo;
//$inc: {score: -1}
    public KeySeq nextSeq(String KeySeqNumber, int inc){

        KeySeq counter = mongo.findAndModify(
                query(where("id").is(KeySeqNumber)),
                new Update().inc("seq", inc),
                options().returnNew(true).upsert(true),
                KeySeq.class);
        return counter;

    }

    public List<KeySeq> findAll(){
        return  mongo.findAll(KeySeq.class);
    }
}
