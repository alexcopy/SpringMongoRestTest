package ru.gpsbox.test.Controller;

import com.google.common.collect.ImmutableList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.gpsbox.test.Entity.Student;
import ru.gpsbox.test.persistance.KeySeqRepo;
import ru.gpsbox.test.persistance.StudentsRepository;
import java.util.Collection;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentsMongoControllerTest {

    private Student student = new Student("AABBCC", 1, "Vova", " Tractor");

    StudentsMongoController studMongContr;
    @Mock
    private StudentsRepository studentsRepository;
    @Mock
    private KeySeqRepo seqRepo;

    @Before
    public void setUp() throws Exception {
        studMongContr = new StudentsMongoController(this.studentsRepository, this.seqRepo);
        studentsRepository.insert(student);
    }

    @Test
    public void getStudentById() {
        //prepare
        when(studentsRepository.findAll()).thenReturn(ImmutableList.of());
        // nextSeq
        Collection<Student> list = studMongContr.getAllStudents();
        //validate
        verify(studentsRepository).findAll();
    }

    @Test
    public void getStudentByName() {
        //prepare
        when(studentsRepository.findStudentByName("Vova")).thenReturn(ImmutableList.of());
        //nextSeq
        Collection<Student> student = studMongContr.getStudentByName("Vova");
        //verify
        verify(studentsRepository).findStudentByName("Vova");
    }

    @Test
    public void deleteStudentById() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        studentsRepository.insert(stud);
        verify(studentsRepository).insert(stud);
        studMongContr.deleteStudentByKeySeq(2);
        verify(studentsRepository).deleteStudentByKeySeq(2);
    }

    @Test
    public void deleteStudentByName() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        studentsRepository.insert(stud);
        verify(studentsRepository).insert(stud);
        studMongContr.deleteStudentByName("Knik");
        verify(studentsRepository).deleteStudentByName("Knik");
    }

    @Test
    public void updateStudent() {
        Student stud = new Student("BBCCDD", 2, "Knik", " Tractorets");
        Student studUpd = new Student("BBCCDD", 2, "Prosp", " Velosipeds");
        studentsRepository.save(stud);
        verify(studentsRepository).save(stud);
        studMongContr.updateStudent(studUpd);
        verify(studentsRepository).save(studUpd);
    }

}