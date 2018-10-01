package ru.gpsbox.test.Service;

import org.springframework.beans.factory.annotation.Autowired;

import ru.gpsbox.test.Entity.KeySeq;
import ru.gpsbox.test.persistance.mongo.KeySeqRepo;


public class NextSequenceService {

    @Autowired
    protected KeySeqRepo mongo;

    public int getNextSequence(String seqName)
    {
//        KeySeq counter = mongo.findAndModify(
//                query(where("id").is(seqName)),
//                new Update().inc("seq",1),
//                options().returnNew(true).upsert(true),
//                KeySeq.class);
//        System.out.println(counter);

//        <T> T findAndModify(Query var1, Update var2, FindAndModifyOptions var3, Class<T> var4);
     return  11;

    }

    public KeySeq test(){


//        MongoOperations  = new MongoTemplate(new MongoClient(), "student");

//        Update update = new Update();
//        update.inc("seq", 1);


//         mongo.findAndModify(query, update, KeySeq.class);



//         this.getNextSequence("keySeq");
        return null;
    }
}
