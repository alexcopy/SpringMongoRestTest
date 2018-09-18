package ru.gpsbox.test.persistance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.Entity.Student;

import java.util.List;



@Repository
public interface StudentsRepository extends MongoRepository<Student, String> {
    List<Student> findStudentByKeySeq(int KeySeq);

    void deleteStudentByKeySeq(int KeySeq);

    void deleteStudentByName(String name);

    List<Student> findStudentByName(String name);
//      void saveStudent(Student student) ;


//    public List<Student> findAll() {
//        return mongoTemplate.findAll(Student.class);
//    }

//    public void save(Map<Integer, Student> students) {
//        students.forEach((k, v) -> {
//                    mongoTemplate.save(v);
//                }
//        );
//        //  mongoTemplate.save(students);
//    }

}
