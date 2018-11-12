package ru.gpsbox.test.web.rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpsbox.test.domain.mongo.Message;
import ru.gpsbox.test.service.MessageService;

import java.util.Collection;

@RestController
@RequestMapping("/")
public class IndexController {

    private final MessageService messageService;

    @Autowired
    public IndexController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public Collection<Message> index() {
        return messageService.getAllMessages();
    }
}
