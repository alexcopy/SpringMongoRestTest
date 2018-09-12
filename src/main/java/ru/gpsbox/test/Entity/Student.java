package ru.gpsbox.test.Entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    private  int id;
    private  String name;
    private  String course;
}
