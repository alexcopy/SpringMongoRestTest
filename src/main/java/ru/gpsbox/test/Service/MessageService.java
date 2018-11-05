package ru.gpsbox.test.Service;

import org.springframework.stereotype.Service;
import ru.gpsbox.test.domain.mongo.Message;
import ru.gpsbox.test.persistance.mongo.MessagesRepository;

import java.util.Collection;
import java.util.List;

@Service
public class MessageService {
    private final MessagesRepository messagesRepository;


    public MessageService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public List<Message> getOneMessageById(String id) {
        return this.messagesRepository.getMessageBy_id(id);
    }

    public Collection<Message> getAllMessages() {
        return this.messagesRepository.findAll();
    }
}
