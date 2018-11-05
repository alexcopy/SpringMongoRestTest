package ru.gpsbox.test.persistance.MysqlDAO;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.domain.mysql.MysqlStudent;

import java.util.List;
@Repository
@Transactional
public interface StudentsMysqlRepository extends JpaRepository<MysqlStudent, String> {

    MysqlStudent findFirstById(int id);

    void deleteById(int id);

    void deleteByName(String name);

    List<MysqlStudent> findByNameOrderById(String name);

    @Modifying
    @Query("update MysqlStudent u set u.name = ?1, u.course = ?2 where u.name = ?1")
    void setStudentByName(String name, String course);

    void deleteByNameAndCourse(String name, String course);
}
