package ru.gpsbox.test.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.gpsbox.test.Dao.FakeStudentDaoImpl;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.persistance.MysqlDAO.StudentsMysqlRepository;
import ru.gpsbox.test.persistance.mongo.StudentsRepository;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {
    private final FakeStudentDaoImpl fakeStudentDaoImpl;
    private final StudentsMysqlRepository mysqlRepository;
    private final StudentsRepository mongoRepository;

    @Autowired
    public StudentService(FakeStudentDaoImpl fakeStudentDaoImpl, StudentsMysqlRepository mysqlRepository, StudentsRepository mongoRepository) {
        this.fakeStudentDaoImpl = fakeStudentDaoImpl;
        this.mysqlRepository = mysqlRepository;
        this.mongoRepository = mongoRepository;
    }

    public Collection<Student> getAllFakeStudents() {
        return this.fakeStudentDaoImpl.getAllStudents();
    }

    public List<Student> getFakeStudenById(String id) {
        return fakeStudentDaoImpl.getStudentById(id);
    }

    public void removeFakeStudentById(String id) {
        this.fakeStudentDaoImpl.removeStudentById(id);
    }

    public List<Student> getFakeStudentByKeySeq(int KeySeq) {
        return fakeStudentDaoImpl.getStudentByKeySeq(KeySeq);
    }

    public void removeFakeStudentByKeySeq(int KeySeq) {
        this.fakeStudentDaoImpl.removeStudentByKeySeq(KeySeq);
    }

    public void updateFakeStudent(Student student) {
        this.fakeStudentDaoImpl.updateStudent(student);
    }

    public void insertFakeStudent(Student student) {
        this.fakeStudentDaoImpl.insertStudentToDb(student);
    }


    public Collection<Student> findAllFromMongo() {
        return mongoRepository.findAll();
    }

    public List<Student> findMongoStudentByName(String name) {
        return mongoRepository.findStudentByName(name);
    }

    public void deleteMongoStudentByKeySeq(int keySeq) {
        mongoRepository.deleteStudentByKeySeq(keySeq);
    }


    public List<Student> findMongoStudentById(String id) {
        return mongoRepository.findStudentBy_id(id);
    }

    public List<Student> findMongoStudentByKeySeq(int keySeq) {
        return mongoRepository.findStudentByKeySeq(keySeq);
    }

    public void deleteMongoStudentById(String id) {
        mongoRepository.deleteStudentBy_id(id);
    }

    public void deleteMongoStudentByName(String name) {
        mongoRepository.deleteStudentByName(name);
    }

    public void saveMongoStudent(Student student) {
        mongoRepository.save(student);
    }

    public void insertMongoStudent(Student student) {
        mongoRepository.insert(student);
    }
}
