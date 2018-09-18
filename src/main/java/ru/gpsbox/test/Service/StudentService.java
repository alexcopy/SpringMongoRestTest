package ru.gpsbox.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;
import ru.gpsbox.test.Dao.FakeStudentDaoImpl;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.persistance.StudentsRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    @Qualifier("fakeData")
    private FakeStudentDaoImpl fakeStudentDaoImpl;
    @Autowired
    private StudentsRepository studentsRepository;

    public Collection<Student> getAllStudents() {
        return this.fakeStudentDaoImpl.getAllStudents();
    }

    public Student getStudenById(int KeySeq) {
        return fakeStudentDaoImpl.getStudenById(KeySeq);
    }

    public void removeStudentById(int KeySeq) {
        this.fakeStudentDaoImpl.removeStudentById(KeySeq);
    }

    public void updateStudent(Student student) {
        this.fakeStudentDaoImpl.updateStudent(student);
    }

    public void insertStudent(Student student) {
        this.fakeStudentDaoImpl.insertStudentToDb(student);
    }


}
