package ru.gpsbox.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.gpsbox.test.domain.mongo.KeySeq;
import ru.gpsbox.test.repository.mongo.KeySeqRepo;

import java.util.List;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class KeySeqService {
    private final MongoTemplate mongo;
    private final KeySeqRepo seqRepo;

    @Autowired
    public KeySeqService(MongoTemplate mongo, KeySeqRepo keySeq) {
        this.mongo = mongo;
        this.seqRepo = keySeq;
    }

    public KeySeq nextSeq(String KeySeqNumber, int inc) {
        KeySeq counter = mongo.findAndModify(
                query(where("id").is(KeySeqNumber)),
                new Update().inc("seq", inc),
                options().returnNew(true).upsert(true),
                KeySeq.class);
        return counter;
    }

    public KeySeq nextSeqByName(String KeySeqName, int inc) {

        KeySeq counter = mongo.findAndModify(
                query(where("name").is(KeySeqName)),
                new Update().inc("seq", inc),
                options().returnNew(true).upsert(true),
                KeySeq.class);
        return counter;
    }

    public List<KeySeq> findAll() {
        return mongo.findAll(KeySeq.class);
    }

    public List<KeySeq> findByName(String name) {
        return seqRepo.findKeySeqByName(name);
    }

    public void save(KeySeq keySeq) {
        this.seqRepo.save(keySeq);
    }

    public void createOrSkip(String id,String name){
        if (this.findByName(name).size() == 0) {
            this.save(new KeySeq(id, name, 0));
        }
    }
}
