package ru.gpsbox.test.Controller;

import com.google.common.collect.ImmutableList;
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
import ru.gpsbox.test.MongoRestApplication;
import ru.gpsbox.test.web.rest.StudentController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
    private String testId = "AAAABBBBCCCCDDDD";

    @Before
    public void setUp() throws Exception {
        localUrl = "http://localhost:" + port + "/students/";
        testStudent = new Student(testId, 5, testName, "Computer");
        insertNewStudent();
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = restTemplate.exchange(localUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
        assertThat(students).isNotNull();
        MatcherAssert.assertThat(students.size(), greaterThan(3));
        ImmutableList<String> names = getAllStudentNames(students);
        MatcherAssert.assertThat(names, containsInAnyOrder("Oleg", "Vasiliy", "Petrovich", "Kolyan", "Test_Vasiliy"));
    }

    @Test
    public void getStudentByKeySeq() {
        List<Student> students = restTemplate.exchange(localUrl + "4", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
        assert students != null;
        MatcherAssert.assertThat(students.size(), is(1));
        MatcherAssert.assertThat(getAllStudentNames(students), containsInAnyOrder("Oleg"));
    }

    @Test
    public void getStudentByID() {
        List<Student> students = restTemplate.exchange(localUrl + "/id/" + testId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();

        assert students != null;
        MatcherAssert.assertThat(students.size(), is(1));
        MatcherAssert.assertThat(getAllStudentNames(students), containsInAnyOrder("Test_Vasiliy"));
    }

    @Test
    public void deleteStudentById() {
        this.restTemplate.delete(localUrl + "/id/" + testId);
        assertThat(this.restTemplate.getForObject(localUrl + "/id/" + testId, String.class)).isEqualTo("[]");
        insertNewStudent();
    }

    @Test
    public void updateStudent() {
        Student student = new Student(testId, 5, "Update_Vasilich", "Update_Computer");
        restTemplate.put(localUrl, student);
        List<Student> students = restTemplate.exchange(localUrl + "/id/" + testId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        }).getBody();
        assert students != null;
        MatcherAssert.assertThat(students.size(), is(1));
        MatcherAssert.assertThat(getAllStudentNames(students), containsInAnyOrder("Update_Vasilich"));
        deleteStudentById();
    }

    @Test
    public void insertStudent() {
        this.restTemplate.delete(localUrl + "/id/" + testId);
        assertThat(this.restTemplate.getForObject(localUrl + "/id/" + testId, String.class)).isEqualTo("[]");
        insertNewStudent();
        getStudentByID();
    }

    public ImmutableList<String> getAllStudentNames(List<Student> actual) {
        return actual.stream()
                .map(Student::getName)
                .collect(
                        collectingAndThen(Collectors.toList(), ImmutableList::copyOf)
                );
    }

    public void insertNewStudent() {
        this.restTemplate.postForObject(
                localUrl,
                testStudent,
                String.class);
    }
}