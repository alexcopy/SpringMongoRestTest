package ru.gpsbox.test.repository.PostgreDAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.domain.postgre.PostgreSqlStudent;



@Repository
public interface StudentsPostgresRepository extends JpaRepository<PostgreSqlStudent, Integer> {

}
