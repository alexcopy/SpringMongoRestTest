package ru.gpsbox.test.domain.mongo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "keySeq")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KeySeq {
    @Id
    private String id;
    private String name;
    private int seq;
}
