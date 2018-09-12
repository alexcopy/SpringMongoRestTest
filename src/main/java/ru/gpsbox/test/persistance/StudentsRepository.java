package ru.gpsbox.test.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import ru.gpsbox.test.Entity.Student;

import java.util.List;


@Repository
public class StudentsRepository
{
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Student> findAll(){
        return mongoTemplate.findAll(Student.class );
    }
}
