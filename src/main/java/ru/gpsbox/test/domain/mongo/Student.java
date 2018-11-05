package ru.gpsbox.test.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    private  String _id;
    private  int keySeq;
    private  String name;
    private  String course;
}
