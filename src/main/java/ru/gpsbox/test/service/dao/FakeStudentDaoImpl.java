package ru.gpsbox.test.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.domain.mongo.Student;
import ru.gpsbox.test.repository.mongo.StudentsRepository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@Qualifier("fakeData")
public class FakeStudentDaoImpl implements StudentDao {
    private final StudentsRepository repository;

    private static Map<Integer, Student> students;

    static {
        students = new HashMap<Integer, Student>() {
            {
                put(1, new Student("5ba0e2a86bc9709d1550cfc3", 1, "Vasiliy", "Computer"));
                put(2, new Student("5ba0d0916bc9709869110512", 2, "Petrovich", "Phisics"));
                put(3, new Student("5ba0d0846bc9709869110511", 3, "Kolyan", "Hernya"));
                put(4, new Student("5ba0d0786bc9709869110510", 4, "Oleg", "Marketing"));
            }
        };
    }

    @Autowired
    public FakeStudentDaoImpl(StudentsRepository repository) {
        this.repository = repository;
    }


    @Override
    public Collection<Student> getAllStudents() {
        return this.students.values();
    }

    @Override
    public List<Student> getStudentByKeySeq(int keySeq) {
        List<Student> stud = new ArrayList<>();
        stud.add(students.get(keySeq));
        return stud;
    }

    @Override
    public void removeStudentByKeySeq(int keySeq) {
        this.students.remove(keySeq);
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

    public List<Student> getStudentById(String id) {
        return findStudentsById(id);
    }

    public List<Student> findStudentsById(String id) {

        return  students.entrySet().parallelStream().filter(st -> st.getValue().get_id().equals(id)).map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public void removeStudentById(String id) {
        students = students.entrySet().parallelStream().filter(st -> !st.getValue().get_id().equals(id)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
//    @PostConstruct
//    public void init() {
//        repository.saveMongoStudent(students);
//    }
}
