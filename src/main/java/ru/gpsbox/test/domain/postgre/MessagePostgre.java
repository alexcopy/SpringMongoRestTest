package ru.gpsbox.test.domain.postgre;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@Table(name = "messages", schema = "messages")
@ToString(of ={"id", "msgtext"})
@EqualsAndHashCode(of={"id"})
public class MessagePostgre {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String msgtext;
}
