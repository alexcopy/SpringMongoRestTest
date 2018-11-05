package ru.gpsbox.test.service.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.domain.mongo.Student;

import java.util.Collection;
import java.util.List;

@Repository
@Qualifier("mongoData")
public class MongoDaoImpl implements StudentDao {
    @Override
    public Collection<Student> getAllStudents() {
        return null;
    }

    @Override
    public List<Student> getStudentByKeySeq(int keySeq) {
        return null;
    }

    @Override
    public List<Student> getStudentById(String id) {
        return null;
    }

    @Override
    public void removeStudentByKeySeq(int keySeq) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void insertStudentToDb(Student student) {

    }
}
