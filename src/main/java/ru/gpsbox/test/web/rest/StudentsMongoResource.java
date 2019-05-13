package ru.gpsbox.test.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.domain.mongo.KeySeq;
import ru.gpsbox.test.domain.mongo.Student;
import ru.gpsbox.test.service.KeySeqService;
import ru.gpsbox.test.service.StudentService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/mongo")
public class StudentsMongoResource {

    private final StudentService studentService;
    private final String keySeqName = "student";

    @Autowired
    public StudentsMongoResource(StudentService studentService, KeySeqService keySeq) {
        this.studentService = studentService;
        this.keySeq = keySeq;
        keySeq.createOrSkip("1", keySeqName);
    }

    private final KeySeqService keySeq;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents() {
        return studentService.findAllFromMongo();
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<Student> getStudentByName(@PathVariable("name") String name) {
        return studentService.findMongoStudentByName(name);
    }

    @RequestMapping(value = "/{KeySeq}", method = RequestMethod.DELETE)
    public void deleteStudentByKeySeq(@PathVariable("KeySeq") int KeySeq) {
        studentService.deleteMongoStudentByKeySeq(KeySeq);
        this.keySeq.nextSeq("1", -1);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public List<Student> getStudentById(@PathVariable("id") String id) {
        return studentService.findMongoStudentById(id);
    }

    @RequestMapping(value = "/{KeySeq}", method = RequestMethod.GET)
    public List<Student> getStudentByKeySeq(@PathVariable("KeySeq") int KeySeq) {
        try {
            return studentService.findMongoStudentByKeySeq(KeySeq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") String id) {
        studentService.deleteMongoStudentById(id);
        this.keySeq.nextSeq("1", -1);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.DELETE)
    public void deleteStudentByName(@PathVariable("name") String name) {
        studentService.deleteMongoStudentByName(name);
        this.keySeq.nextSeq("1", -1);
    }

    @RequestMapping(value = "/{KeySeq}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student updateStudent(@PathVariable("KeySeq") Integer KeySeq, @RequestBody Student student) {
        Student studentByKeySeq = null;
        try {
            studentByKeySeq = studentService.findOneMongoStudentByKeySeq(KeySeq);
            studentByKeySeq.setName(student.getName());
            studentByKeySeq.setCourse(student.getCourse());
            studentService.saveMongoStudent(studentByKeySeq);
            return studentByKeySeq;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentByKeySeq;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Student insertStudent(@RequestBody Student student) {
        student.setKeySeq(this.keySeq.nextSeq("1", 1).getSeq());
        studentService.insertMongoStudent(student);
        return student;
    }

    @RequestMapping(value = "/seq", method = RequestMethod.GET)
    public List<KeySeq> getSeq() {
        return keySeq.findAll();
    }
}
