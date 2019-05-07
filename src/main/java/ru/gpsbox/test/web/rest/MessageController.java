package ru.gpsbox.test.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gpsbox.test.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("letsmessage")
public class MessageController {

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public  Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(message -> message.get("id").equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }

    public List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("message", "FirstMessage");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("message", "SecondMessage");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("message", "ThirdMessage");
        }});
    }};

}
