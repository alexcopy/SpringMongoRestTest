package ru.gpsbox.test.persistance.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gpsbox.test.Entity.Student;

import java.util.List;

@Transactional
@Repository
public interface StudentsRepository extends MongoRepository<Student, String> {
    List<Student> findStudentByKeySeq(int KeySeq);

    void deleteStudentByKeySeq(int KeySeq);

    List<Student>  findStudentBy_id(String id);

    void deleteStudentByName(String name);

    List<Student> findStudentByName(String name);

    void deleteStudentBy_id(String id);
//      void saveStudent(Student student) ;


//    public List<Student> getAllStudents() {
//        return mongoTemplate.getAllStudents(Student.class);
//    }

//    public void save(Map<Integer, Student> students) {
//        students.forEach((k, v) -> {
//                    mongoTemplate.save(v);
//                }
//        );
//        //  mongoTemplate.save(students);
//    }

}
