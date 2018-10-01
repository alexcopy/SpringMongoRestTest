package ru.gpsbox.test.EntityMysql;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Entity
@Table(name = "students")
@Document(collection="students")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentsSql {
    @org.springframework.data.annotation.Id
    @Id
    private int id;
    private int keySeq;
    @Field
    private String name;
    @Field
    private  String course;
}
