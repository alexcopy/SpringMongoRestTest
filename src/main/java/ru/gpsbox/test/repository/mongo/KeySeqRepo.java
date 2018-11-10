package ru.gpsbox.test.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.domain.mongo.KeySeq;

import java.util.List;

@Repository
public interface KeySeqRepo extends MongoRepository<KeySeq, String> {
    List<KeySeq> findKeySeqByName(String name);
}
