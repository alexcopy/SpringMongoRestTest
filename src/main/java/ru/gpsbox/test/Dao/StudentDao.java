package ru.gpsbox.test.Dao;

import ru.gpsbox.test.Entity.Student;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface StudentDao {
    Collection<Student> getAllStudents();

    List<Student> getStudentByKeySeq(int keySeq);
    List<Student> getStudentById(String id);

    void removeStudentByKeySeq(int keySeq);

    void updateStudent(Student student);

    void insertStudentToDb(Student student);

    default void forEvery(Student action){
        Objects.requireNonNull(action);
    }
}
