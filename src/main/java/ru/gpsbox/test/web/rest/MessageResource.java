package ru.gpsbox.test.web.rest;


import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.domain.mongo.Message;
import ru.gpsbox.test.service.KeySeqService;
import ru.gpsbox.test.service.MessageService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageResource {

    private final MessageService messageService;
    private final KeySeqService keySeq;
    private static final String keySeqName = "message";

    public MessageResource(MessageService messageService, KeySeqService keySeq) {
        this.messageService = messageService;
        this.keySeq = keySeq;
        keySeq.createOrSkip("2", keySeqName);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Message> list() {
        return messageService.getAllMessages();
    }

    @GetMapping("/id/{id}")
    public Message getOneMessage(@PathVariable String id) {
        return messageService.getOneMessageById(id);
    }

    @GetMapping("/name/{name}")
    public List<Message> getOneMessageByName(@PathVariable String name) {
        return messageService.getOneMessageByName(name);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") String id) {
        messageService.deleteMessageById(id);
        this.keySeq.nextSeqByName("message", -1);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        messageService.save(message);
        return message;
    }

    @PutMapping("/id/{id}")
    public Message update(@PathVariable("id") Message messageFromDb, @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDb, "id", "_id");
        return messageService.saveUpdate(message);
    }
}
