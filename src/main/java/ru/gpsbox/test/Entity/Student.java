package ru.gpsbox.test.Entity;

import lombok.*;
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
