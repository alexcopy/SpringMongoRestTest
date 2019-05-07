package ru.gpsbox.test.domain.mysql;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


@Entity
@Table(name = "students")
@Document(collection = "students")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MysqlStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false )
    private String course;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    @CreationTimestamp
    private Date CreatedAt;

    @Column(name = "LastModifiedAt", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date LastModifiedAt;
}
