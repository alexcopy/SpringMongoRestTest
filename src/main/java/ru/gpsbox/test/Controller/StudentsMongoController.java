package ru.gpsbox.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.Entity.KeySeq;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.persistance.KeySeqRepo;
import ru.gpsbox.test.persistance.StudentsRepository;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/mongo")
public class StudentsMongoController {

    private final StudentsRepository repository;

    @Autowired
    public StudentsMongoController(StudentsRepository repository, KeySeqRepo keySeq) {
        this.repository = repository;
        this.keySeq = keySeq;
    }

    private final KeySeqRepo keySeq;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents() {
        return  repository.findAll();
    }

    @RequestMapping(value = "/{KeySeq}", method = RequestMethod.GET)
    public List<Student> getStudentById(@PathVariable("KeySeq") int KeySeq) {
        return repository.findStudentByKeySeq(KeySeq);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<Student> getStudentByName(@PathVariable("name") String name) {
        return repository.findStudentByName(name);
    }

    @RequestMapping(value = "/{KeySeq}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("KeySeq") int KeySeq) {
        repository.deleteStudentByKeySeq(KeySeq);
    }


    @RequestMapping(value = "/name/{name}", method = RequestMethod.DELETE)
    public void deleteStudentByName(@PathVariable("name") String name) {
        repository.deleteStudentByName(name);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student) {
        repository.save(student);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student) {
        student.setKeySeq(this.keySeq.test("1").getSeq());
        repository.insert(student);
    }

    @RequestMapping(value = "/seq", method = RequestMethod.GET)
    public List<KeySeq> getSeq() {
        return keySeq.findAll();
    }
}
