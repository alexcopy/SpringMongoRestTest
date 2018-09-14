package ru.gpsbox.test.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.Entity.Student;

import java.util.List;
import java.util.Map;


@Repository
public interface StudentsRepository extends MongoRepository <Student, String> {
      List<Student> findStudentById(int id);
      void deleteStudentById(int id);

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
