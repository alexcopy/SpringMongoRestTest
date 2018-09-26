package ru.gpsbox.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public List<Student> getStudenById(String id) {
        return fakeStudentDaoImpl.getStudentById(id);
    }

    public void removeStudentById(String id) {
        this.fakeStudentDaoImpl.removeStudentById(id);
    }

    public List<Student> getStudenByKeySeq(int KeySeq) {
        return fakeStudentDaoImpl.getStudentByKeySeq(KeySeq);
    }

    public void removeStudentByKeySeq(int KeySeq) {
        this.fakeStudentDaoImpl.removeStudentByKeySeq(KeySeq);
    }


    public void updateStudent(Student student) {
        this.fakeStudentDaoImpl.updateStudent(student);
    }

    public void insertStudent(Student student) {
        this.fakeStudentDaoImpl.insertStudentToDb(student);
    }


}
