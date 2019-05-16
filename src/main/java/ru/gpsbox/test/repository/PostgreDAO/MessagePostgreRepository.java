package ru.gpsbox.test.repository.PostgreDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gpsbox.test.domain.postgre.MessagePostgre;

public interface MessagePostgreRepository  extends JpaRepository<MessagePostgre, Long> {

}
