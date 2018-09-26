package ru.gpsbox.test.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.Service.StudentService;
import ru.gpsbox.test.persistance.StudentsRepository;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }



    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public List<Student> getStudentById(@PathVariable("id") String id) {
        return studentService.getStudenById(id);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") String id) {
        studentService.removeStudentById(id);
    }


    @RequestMapping(value = "/{KeySeq}", method = RequestMethod.GET)
    public List<Student> getStudentById(@PathVariable("KeySeq") int KeySeq) {
        return studentService.getStudenByKeySeq(KeySeq);
    }

    @RequestMapping(value = "/{KeySeq}", method = RequestMethod.DELETE)
    public void deleteStudentByKeySeq(@PathVariable("KeySeq") int KeySeq) {
        studentService.removeStudentByKeySeq(KeySeq);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student) {
        studentService.insertStudent(student);
    }
}
