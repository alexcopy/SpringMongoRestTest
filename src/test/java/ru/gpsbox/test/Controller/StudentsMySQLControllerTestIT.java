package ru.gpsbox.test.Controller;

import com.google.common.collect.ImmutableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import ru.gpsbox.test.domain.mysql.MysqlStudent;
import ru.gpsbox.test.MongoRestApplication;
import ru.gpsbox.test.web.rest.StudentsMySQLController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StudentsMySQLControllerTestIT {

    @Autowired
    private StudentsMySQLController controller;
    private MysqlStudent newStudent;
    /* in case we need to send authentication */
    private HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String studentTestName = "TestStudentName";
    private String studentTestCourse = "TestStudentCourse";
    private List<Integer> studentTestIds;
    private String localUrl = "http://localhost";

    @Before
    public void setUp() throws Exception {
        this.newStudent = new MysqlStudent();
        this.newStudent.setName(this.studentTestName);
        this.newStudent.setCourse(this.studentTestCourse);
        localUrl = "http://localhost:" + port + "/mysql/";
        this.restTemplate.postForObject(localUrl, newStudent, String.class);
        this.restTemplate.postForObject(localUrl, newStudent, String.class);
        assertThat(this.restTemplate.getForObject(localUrl + "name/" + studentTestName, String.class)).isNotEqualTo("[]");
        setStudentsIds();
    }

    private void setStudentsIds() {
        List<MysqlStudent> actual = this.restTemplate.exchange(
                localUrl + "name/" + this.studentTestName,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MysqlStudent>>() {
                }).getBody();
        this.getIdsFromInsertedStudents(actual);
    }

    @Test
    public void getAllStudents() {
        String students = this.restTemplate.getForObject(localUrl, String.class);
        assertThat(students).contains(studentTestName);
        assertThat(students).contains(studentTestCourse);
    }

    @Test
    public void getStudentByName() {
        String students = this.restTemplate.getForObject(localUrl + "name/" + studentTestName, String.class);
        assertThat(students).contains(studentTestName);
        assertThat(students).contains(studentTestCourse);
    }

    @Test
    public void getStudentById() {
        studentTestIds.parallelStream().forEach((entry) ->
                {
                    MysqlStudent student = this.restTemplate.getForObject(localUrl + "/" + entry, MysqlStudent.class);
                    assertThat(studentTestName).isEqualTo(student.getName());
                }
        );
    }

    @Test
    public void updateStudent() {
        studentTestIds.parallelStream().forEach((entry) ->
                {
                    MysqlStudent student = this.restTemplate.getForObject(localUrl + "/" + entry, MysqlStudent.class);
                    student.setCourse("Updated Course");
                    assertThat(student.getLastModifiedAt()).isNull();
                    this.restTemplate.put(localUrl, student);
                    MysqlStudent updatedStudent = this.restTemplate.getForObject(localUrl + "/" + entry, MysqlStudent.class);
                    assertThat(updatedStudent.getLastModifiedAt()).isNotNull();
                    assertThat(updatedStudent.getCourse()).isEqualTo("Updated Course");
                }
        );
    }


    @Test
    public void deleteStudentById() {
        studentTestIds.parallelStream().forEach((entry) ->
                {
                    MysqlStudent student = this.restTemplate.getForObject(localUrl + "/" + entry, MysqlStudent.class);
                    this.restTemplate.delete(localUrl + entry);
                }
        );
        assertThat(this.restTemplate.getForObject(localUrl + "name/" + studentTestName, String.class)).isEqualTo("[]");
        this.restTemplate.postForObject(localUrl, newStudent, String.class);
        this.restTemplate.postForObject(localUrl, newStudent, String.class);
        setStudentsIds();
    }

    public void getIdsFromInsertedStudents(List<MysqlStudent> actual) {
        this.studentTestIds = actual.stream()
                .map(MysqlStudent::getId)
                .collect(
                        collectingAndThen(Collectors.toList(), ImmutableList::copyOf)
                );
    }

    @After
    public void tearDown() throws Exception {
        this.restTemplate.delete(localUrl + "name/" + studentTestName);
        assertThat(this.restTemplate.getForObject(localUrl + "name/" + studentTestName, String.class)).isEqualTo("[]");
        System.out.println(this.restTemplate.getForObject(localUrl, String.class));
    }
}