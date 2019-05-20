package ru.gpsbox.test.web.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.domain.postgre.MessagePostgre;
import ru.gpsbox.test.domain.postgre.PostgreViews;
import ru.gpsbox.test.repository.PostgreDAO.MessagePostgreRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("lets/messages")
public class LetsMessageResource {

    private int counter = 4;
    private final MessagePostgreRepository messages;

    @Autowired
    public LetsMessageResource(MessagePostgreRepository messages) {
        this.messages = messages;
    }



    @GetMapping
    @JsonView(PostgreViews.IdName.class)
    public List<MessagePostgre> list() {
        return messages.findAll();
    }

    @GetMapping("{id}")
    @JsonView(PostgreViews.FullMsg.class)
    public MessagePostgre getOne(@PathVariable("id") MessagePostgre message) {
        return message;
    }

    @PostMapping
    public MessagePostgre create(@RequestBody MessagePostgre message) {
        message.setCreationDate(LocalDateTime.now());
        return messages.save(message);
    }


    @PutMapping("{id}")
    public MessagePostgre update(
            @PathVariable("id") MessagePostgre messageFromDb,
            @RequestBody MessagePostgre message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messages.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") MessagePostgre message){

    messages.delete(message);
    }
}
