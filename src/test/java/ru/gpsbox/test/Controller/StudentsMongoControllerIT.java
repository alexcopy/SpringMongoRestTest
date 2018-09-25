package ru.gpsbox.test.Controller;

import com.google.common.collect.ImmutableList;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gpsbox.test.Entity.Student;

import static java.util.stream.Collectors.collectingAndThen;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;


import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class StudentsMongoControllerIT {

    @Autowired
    private StudentsMongoController studentsMongoController;
    Student newStudent;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String studentTestName = "TestStudentName";
    private String studentTestId = "BBCCDDDDDD";

    @Before
    public void setUp() throws Exception {
        this.newStudent = new Student(studentTestId, 100, studentTestName, " Tractorets");
        insertNewStudent();
    }

    public void insertNewStudent() {
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/mongo",
                this.newStudent,
                String.class);
    }

    @After
    public void tearDown() throws Exception {

        this.restTemplate.delete("http://localhost:" + port + "/mongo/name/" + studentTestName);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mongo/name/" + studentTestName, String.class)).isEqualTo("[]");
    }

    @Test
    public void testDeleteByName() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mongo/name/" + studentTestName, String.class)).isNotEqualTo("[]");
        this.restTemplate.delete("http://localhost:" + port + "/mongo/name/" + studentTestName);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mongo/name/" + studentTestName, String.class)).isEqualTo("[]");
        insertNewStudent();
    }


    @DisplayName("given object to save"
            + " when save object using MongoDB template"
            + " then object is saved")

    @Test
    public void contextLoads() throws Exception {
        assertThat(this.studentsMongoController).isNotNull();
    }

    @Test
    public void testEndPointGetByName() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mongo/name/" + studentTestName, String.class)).isNotNull();
        ResponseEntity<List<Student>> entity = this.restTemplate.exchange("http://localhost:" + port + "/mongo/name/" + studentTestName, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        });
        assertThat(entity).isNotNull();
        List<Student> actual = entity.getBody();
        //validate
        assert actual != null;
        List<String> actualNames = actual.stream()
                .map(Student::getName)
                .collect(
                        collectingAndThen(Collectors.toList(), ImmutableList::copyOf)
                );
        MatcherAssert.assertThat(actualNames, containsInAnyOrder(this.studentTestName));
        MatcherAssert.assertThat(actual.size(), is(1));
    }

    @Test
    public void getAllStudentsShouldReturnAtLeastOneStudent() throws Exception {
        String students = this.restTemplate.getForObject("http://localhost:" + port + "/mongo",
                String.class);
        assertThat(students).contains("name");
    }
}