package ru.gpsbox.test.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.Entity.KeySeq;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.Service.StudentService;
import ru.gpsbox.test.persistance.mongo.KeySeqRepo;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/mongo")
public class StudentsMongoController {

    private final StudentService studentService;

    @Autowired
    public StudentsMongoController(StudentService studentService, KeySeqRepo keySeq) {
        this.studentService = studentService;
        this.keySeq = keySeq;
    }

    private final KeySeqRepo keySeq;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents() {
        return  studentService.findAllFromMongo();
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
        return studentService.findMongoStudentByKeySeq(KeySeq);
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

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student) {
        studentService.saveMongoStudent(student);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student) {
        student.setKeySeq(this.keySeq.nextSeq("1", 1).getSeq());
        studentService.insertMongoStudent(student);
    }

    @RequestMapping(value = "/seq", method = RequestMethod.GET)
    public List<KeySeq> getSeq() {
        return keySeq.findAll();
    }
}
