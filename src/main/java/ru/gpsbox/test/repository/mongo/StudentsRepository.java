package ru.gpsbox.test.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gpsbox.test.domain.mongo.Student;

import java.util.List;

@Transactional
@Repository
public interface StudentsRepository extends MongoRepository<Student, String> {
    List<Student> findFirstStudentByKeySeq(int KeySeq);

    void deleteStudentByKeySeq(int KeySeq);

    List<Student>  findStudentBy_id(String id);

    void deleteStudentByName(String name);

    List<Student> findStudentByName(String name);

    void deleteStudentBy_id(String id);

    Student findFirstStudentByKeySeq(Integer keySeq);

//      void saveStudent(Student student) ;


//    public List<Student> getAllFakeStudents() {
//        return mongoTemplate.getAllFakeStudents(Student.class);
//    }

//    public void saveMongoStudent(Map<Integer, Student> students) {
//        students.forEach((k, v) -> {
//                    mongoTemplate.saveMongoStudent(v);
//                }
//        );
//        //  mongoTemplate.saveMongoStudent(students);
//    }

}
