package ru.gpsbox.test.web.rest;

import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("lets/messages")
public class MessageController {

    private int counter = 4;

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getMessage(id);
    }

    private Map<String, String> getMessage(String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }

    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("msgtext", "FirstMessage");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("msgtext", "Second Message");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("msgtext", "ThirdMessage");
        }});
    }};

    @PostMapping
    public Map<String, String> create(@RequestBody Map<String, String> message) {
        message.put("id", String.valueOf(counter++));
        messages.add(message);
        return message;
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> message) {
        Map<String, String> messageFromDb = getMessage(id);
        messageFromDb.putAll(message);
        messageFromDb.put("id", id);
        return messageFromDb;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Map<String, String> message = getMessage(id);
        messages.remove(message);
    }
}
