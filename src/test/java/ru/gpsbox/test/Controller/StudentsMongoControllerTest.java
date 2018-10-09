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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;
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

    @Test
    public void testLambdaAndStreams() {
        Collection<Integer> number
                = IntStream.range(0, 10).boxed().collect(toSet());
        number.parallelStream().forEach(System.out::println);
        number.forEach(System.out::println);

        Function<String, String> mapper1 = String::toUpperCase; //method reference
        Function<String, Integer> mapp2 = Integer::new; //method reference
        Function<String, String> mapper2 = x->x.toUpperCase(); // Lambda
        Function<String, Integer> mapp1 = x->new Integer(x); //Lambda

        System.out.println(mapper1.apply("Vasiliy Petrovich"));
        System.out.println(mapper2.apply("Vasiliy Petrovich"));
        System.out.println(mapp1.apply("22"));
        System.out.println(mapp2.apply("22"));
    }
}