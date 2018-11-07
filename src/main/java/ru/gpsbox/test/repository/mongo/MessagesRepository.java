package ru.gpsbox.test.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gpsbox.test.domain.mongo.Message;

import java.util.List;


@Transactional
@Repository
public interface MessagesRepository extends MongoRepository<Message, String> {

    List<Message> getMessageBy_id(String id);
    Message findFirstBy_id(String id);
    List<Message> getMessageByName(String name);
}
