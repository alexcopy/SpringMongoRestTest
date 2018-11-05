package ru.gpsbox.test.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    @Id
    private  String _id;
    private  String name;
    private  String message;
}
