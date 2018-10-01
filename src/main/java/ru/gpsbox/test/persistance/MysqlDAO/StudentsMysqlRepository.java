package ru.gpsbox.test.persistance.MysqlDAO;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.gpsbox.test.EntityMysql.MysqlStudent;

@Transactional
public interface StudentsMysqlRepository extends JpaRepository<MysqlStudent, String> {

}
