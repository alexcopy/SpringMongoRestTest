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

    StudentsMongoController studMongContr;
    @Mock
    private StudentsRepository studentsRepository;
    @Mock
    private KeySeqRepo seqRepo;

    @Before
    public void setUp() throws Exception {
        studMongContr = new StudentsMongoController(this.studentsRepository, this.seqRepo);
    }



    @Test
    public void getStudentById() {
        //prepare
        when(studentsRepository.findAll()).thenReturn(ImmutableList.of());
        // test
        Collection<Student> list = studMongContr.getAllStudents();
        //validate
        verify(studentsRepository).findAll();

    }

    @Test
    public void getStudentByName() {
    }

    @Test
    public void deleteStudentById() {
    }

    @Test
    public void deleteStudentByName() {
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void insertStudent() {
    }
}