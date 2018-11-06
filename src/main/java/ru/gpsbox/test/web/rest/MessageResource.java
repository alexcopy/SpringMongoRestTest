package ru.gpsbox.test.web.rest;


import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.domain.mongo.KeySeq;
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

    @GetMapping("{id}")
    public List<Message> getOneMessage(@PathVariable String id) {
        return messageService.getOneMessageById(id);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") String id) {
        messageService.deleteMessageById(id);
        this.keySeq.nextSeqByName("message", -1);
    }


    @PatchMapping
    public List<String> create(@RequestBody List<Message> message) {
        return null;
    }
}
