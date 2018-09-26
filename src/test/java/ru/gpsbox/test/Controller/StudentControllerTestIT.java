package ru.gpsbox.test.Controller;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gpsbox.test.Entity.Student;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTestIT {

    @Autowired
    StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String localUrl = "http://localhost";

    private Student testStudent;
    private String testName = "Test_Vasiliy";
    private String id = "AAAABBBBCCCCDDDD";

    @Before
    public void setUp() throws Exception {
        localUrl = "http://localhost:" + port + "/students/";

        testStudent = new Student(id, 5, testName, "Computer");
    }


    @Test
    public void testGetAllStudents() {
        List<Student> students = restTemplate.exchange(localUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
        assertThat(students).isNotNull();
        MatcherAssert.assertThat(students.size(), greaterThan(3));
        ImmutableList<String> names = getAllStudentNames(students);
        MatcherAssert.assertThat(names, containsInAnyOrder("Oleg", "Vasiliy", "Petrovich", "Kolyan"));
    }

    @Test
    public void getStudentByKeySeq() {
        List<Student> students = restTemplate.exchange(localUrl + "4", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
        assert students != null;
        MatcherAssert.assertThat(students.size(), is(1));
        MatcherAssert.assertThat(getAllStudentNames(students), containsInAnyOrder( "Oleg"));
    }

    @Test
    public void getStudentByID() {
        List<Student> students = restTemplate.exchange(localUrl + "/id/5ba0d0916bc9709869110512", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
        assert students != null;
        MatcherAssert.assertThat(students.size(), is(1));
        MatcherAssert.assertThat(getAllStudentNames(students), containsInAnyOrder( "Petrovich"));
    }

    @Test
    public void deleteStudentById() {
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void insertStudent() {
    }

    public ImmutableList<String> getAllStudentNames(List<Student> actual) {
        return actual.stream()
                .map(Student::getName)
                .collect(
                        collectingAndThen(Collectors.toList(), ImmutableList::copyOf)
                );
    }

    @Test
    public void insertNewStudent() {
//        this.restTemplate.postForObject(
//                "http://localhost:" + port ,
//                this.testStudent,
//                String.class);
//
        List<Student> actual = this.restTemplate.exchange(localUrl + "/id/" + "5ba0e2b66bc9709d1550cfc4", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
//        assert actual != null;
//        Student student = Iterables.tryFind(actual,
//                testStudent -> {
//                    assert testStudent != null;
//                    return studentTestName.equals(testStudent.getName());
//                }).orNull();
//        assert student != null;
//        this.newStudent = student;
    }
}