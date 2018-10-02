package ru.gpsbox.test.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.EntityMysql.MysqlStudent;
import ru.gpsbox.test.persistance.MysqlDAO.StudentsMysqlRepository;

import java.util.List;

@RestController
@RequestMapping("/mysql")
public class StudentsMySQLController {
    private final StudentsMysqlRepository repository;

    public StudentsMySQLController(StudentsMysqlRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MysqlStudent> getAllStudents() {
        return repository.findAll();
    }


    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<MysqlStudent> getStudentByName(@PathVariable("name") String name) {
        return repository.findByNameOrderById(name);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MysqlStudent getStudentById(@PathVariable("id") int id) {
        return repository.findFirstById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody MysqlStudent student) {

        repository.setStudentByName(student.getName(), student.getCourse());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody MysqlStudent student) {
        repository.save(student);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id) {
        repository.deleteById(id);
    }

    @RequestMapping(value = "/student", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudentByStudent(@RequestBody MysqlStudent student) {
        repository.deleteByNameAndCourse(student.getName(), student.getCourse());
    }


    @RequestMapping(value = "/name/{name}", method = RequestMethod.DELETE)
    public void deleteStudentByName(@PathVariable("name") String name) {
        repository.deleteByName(name);
    }
}
