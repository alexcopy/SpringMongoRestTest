package ru.gpsbox.test.persistance.MysqlDAO;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.gpsbox.test.EntityMysql.StudentsSql;

@Transactional
public interface StudentsMysql extends JpaRepository<StudentsSql, String> {

}
