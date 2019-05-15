package ru.gpsbox.test.domain.postgre;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "students")

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostgreSqlStudent {

    @Id
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false )
    private String course;

}
