package ru.gpsbox.test.Dao;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.Entity.Student;

import java.util.Collection;

@Repository
@Qualifier("mongoData")
public class MongoDaoImpl implements StudentDao {
    @Override
    public Collection<Student> getAllStudents() {
        return null;
    }

    @Override
    public Student getStudenById(int id) {
        return null;
    }

    @Override
    public void removeStudentById(int id) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void insertStudentToDb(Student student) {

    }
}
