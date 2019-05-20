package ru.gpsbox.test.domain.postgre;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "messages")
@ToString(of ={"id", "msgtext"})
@EqualsAndHashCode(of={"id"})
public class MessagePostgre {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonView(PostgreViews.Id.class)
    private Long id;
    @JsonView(PostgreViews.IdName.class)
    private String msgtext;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView(PostgreViews.FullMsg.class)
    private LocalDateTime creationDate;
}
