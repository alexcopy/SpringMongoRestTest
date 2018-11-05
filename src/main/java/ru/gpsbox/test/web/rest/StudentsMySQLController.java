package ru.gpsbox.test.web.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.domain.mysql.MysqlStudent;
import ru.gpsbox.test.Service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/mysql")
public class StudentsMySQLController {
    private final StudentService studentService;

    public StudentsMySQLController(StudentService repository) {
        this.studentService = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<MysqlStudent> getAllStudents() {
        return studentService.findAllSqlStudents();
    }


    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public List<MysqlStudent> getStudentByName(@PathVariable("name") String name) {
        return studentService.findSqlStudentByNameOrderById(name);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public MysqlStudent getStudentById(@PathVariable("id") int id) {
        return studentService.findFirstSqlStudentById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody MysqlStudent student) {
        studentService.setSqlStudentByName(student);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody MysqlStudent student) {
        studentService.saveSqlStudent(student);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id) {
        studentService.deleteSqlStudentById(id);
    }

    @RequestMapping(value = "/student", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteStudentByStudent(@RequestBody MysqlStudent student) {
        studentService.deleteSqlStudentByNameAndCourse(student.getName(), student.getCourse());
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.DELETE)
    public void deleteStudentByName(@PathVariable("name") String name) {
        studentService.deleteSqlStudentByName(name);
    }
}
