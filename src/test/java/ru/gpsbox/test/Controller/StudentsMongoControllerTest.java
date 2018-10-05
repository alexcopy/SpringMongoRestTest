package ru.gpsbox.test.Controller;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.Service.StudentService;
import ru.gpsbox.test.persistance.mongo.KeySeqRepo;

import java.util.Collection;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StudentsMongoControllerTest {

    private Student student = new Student("AABBCC", 1, "Vova", " Tractor");

    StudentsMongoController studMongContr;
    @Mock
    private StudentService studentService;
    @Mock
    private KeySeqRepo seqRepo;

    @Before
    public void setUp() throws Exception {
        studMongContr = new StudentsMongoController(this.studentService, this.seqRepo);
        studentService.insertMongoStudent(student);
    }

    @Test
    public void getStudentById() {
        //prepare
        when(studentService.findAllFromMongo()).thenReturn(ImmutableList.of());
        // nextSeq
        Collection<Student> list = studMongContr.getAllStudents();
        //validate
        verify(studentService).findAllFromMongo();
    }

    @Test
    public void getStudentByName() {
        //prepare
        when(studentService.findMongoStudentByName("Vova")).thenReturn(ImmutableList.of());
        //nextSeq
        Collection<Student> student = studMongContr.getStudentByName("Vova");
        //verify
        verify(studentService).findMongoStudentByName("Vova");
    }

    @Test
    public void deleteStudentById() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        studentService.insertMongoStudent(stud);
        verify(studentService).insertMongoStudent(stud);
        studMongContr.deleteStudentByKeySeq(2);
        verify(studentService).deleteMongoStudentByKeySeq(2);
    }

    @Test
    public void deleteStudentByName() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        studentService.insertMongoStudent(stud);
        verify(studentService).insertMongoStudent(stud);
        studMongContr.deleteStudentByName("Knik");
        verify(studentService).deleteMongoStudentByName("Knik");
    }

    @Test
    public void updateStudent() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        Student studUpd = new Student("BBCCDD", 2, "Prosp", " Velosipeds");
        studentService.saveMongoStudent(stud);
        verify(studentService).saveMongoStudent(stud);
        studMongContr.updateStudent(studUpd);
        verify(studentService).saveMongoStudent(studUpd);
    }

}