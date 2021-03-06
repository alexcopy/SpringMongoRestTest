package ru.gpsbox.test.service;

import org.springframework.stereotype.Service;
import ru.gpsbox.test.domain.mongo.Message;
import ru.gpsbox.test.repository.mongo.MessagesRepository;

import java.util.Collection;
import java.util.List;

@Service
public class MessageService {
    private final MessagesRepository messagesRepository;

    public MessageService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }




    public Message getOneMessageById(String id) {
        return this.messagesRepository.findFirstBy_id(id);
    }

    public Collection<Message> getAllMessages() {
        return this.messagesRepository.findAll();
    }

    public void deleteMessageById(String id) {
        this.messagesRepository.deleteById(id);
    }

    public void save(Message message) {
        messagesRepository.save(message);
    }

    public Message findMessageById(String id) {
        return messagesRepository.findFirstBy_id(id);
    }


    public Message saveUpdate(Message message) {
        messagesRepository.save(message);
        return message;
    }

    public List<Message> getOneMessageByName(String name) {
        return messagesRepository.getMessageByName(name);
    }
}
