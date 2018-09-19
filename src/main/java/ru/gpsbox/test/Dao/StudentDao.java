package ru.gpsbox.test.Dao;

import ru.gpsbox.test.Entity.Student;

import java.util.Collection;

public interface StudentDao {
    Collection<Student> getAllStudents();

    Student getStudentByKeySeq(int keySeq);

    void removeStudentByKeySeq(int keySeq);

    void updateStudent(Student student);

    void insertStudentToDb(Student student);
}
