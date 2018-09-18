package ru.gpsbox.test.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.persistance.StudentsRepository;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
@Qualifier("fakeData")
public class FakeStudentDaoImpl implements StudentDao {
    @Autowired
    private StudentsRepository repository;

    private static Map<Integer, Student> students;

    static {
        students = new HashMap<Integer, Student>() {
            {

            }
        };
    }


    @Override
    public Collection<Student> getAllStudents() {
        return this.students.values();
    }
    @Override
    public Student getStudenById(int KeySeq) {
        return this.students.get(KeySeq);
    }
    @Override
    public void removeStudentById(int KeySeq) {
        this.students.remove(KeySeq);
    }
    @Override
    public void updateStudent(Student student) {
        Student st = students.get(student.getKeySeq());
        st.setCourse(student.getCourse());
        st.setName(student.getName());
        students.put(student.getKeySeq(), student);
    }

    @Override
    public void insertStudentToDb(Student student) {
        this.students.put(student.getKeySeq(), student);

    }
//    @PostConstruct
//    public void init() {
//        repository.save(students);
//    }
}
