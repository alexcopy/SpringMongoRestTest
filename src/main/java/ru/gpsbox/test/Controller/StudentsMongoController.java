package ru.gpsbox.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.persistance.StudentsRepository;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/mongo")
public class StudentsMongoController {

    private final StudentsRepository repository;

    @Autowired
    public StudentsMongoController(StudentsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping( method = RequestMethod.GET)
    public Collection<Student> findAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Student> getStudentById(@PathVariable("id") int id) {
        return repository.findStudentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id) {
        repository.deleteStudentById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student ) {
        repository.save(student);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student ) {
        repository.save(student);
    }
}
