package ru.gpsbox.test.Controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gpsbox.test.EntityMysql.MysqlStudent;
import ru.gpsbox.test.MongoRestApplication;

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
    private int studentTestID;
    private String localUrl = "http://localhost";

    @Before
    public void setUp() throws Exception {
        this.newStudent = new MysqlStudent();
        this.newStudent.setName(this.studentTestName);
        this.newStudent.setCourse(this.studentTestCourse);
        localUrl = "http://localhost:" + port + "/mysql/";
        this.restTemplate.postForObject(localUrl, newStudent, String.class);
        this.restTemplate.postForObject(localUrl, newStudent, String.class);
        assertThat(this.restTemplate.getForObject(localUrl+"name/"+studentTestName , String.class)).isNotEqualTo("[]");
    }

    @Test
    public void getAllStudents() {


    }

    @Test
    public void getStudentByName() {
    }

    @Test
    public void getStudentById() {
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void insertStudent() {
    }

    @Test
    public void deleteStudentById() {
    }

    public void insertNewStudent() {
        this.restTemplate.postForObject(localUrl, newStudent, String.class);
    }

    @After
    public void tearDown() throws Exception {
        this.restTemplate.delete(localUrl+"name/"+studentTestName);
        System.out.println(this.restTemplate.getForObject(localUrl , String.class));
    }
}