package ru.gpsbox.test.web.rest;


import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.domain.mongo.Message;
import ru.gpsbox.test.Service.MessageService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Message> list() {
        return messageService.getAllMessages();
    }

    @GetMapping("{id}")
    public List<Message> getOneMessage(@PathVariable String id) {
        return messageService.getOneMessageById(id);
    }

}
