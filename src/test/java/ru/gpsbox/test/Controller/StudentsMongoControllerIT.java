package ru.gpsbox.test.Controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import ru.gpsbox.test.Entity.Student;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

public class StudentsMongoControllerIT {

    @Autowired
    private StudentsMongoController student;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/mongo",
                new Student("BBCCDDDDDD", 100, "Knik", " Tractorets"),
                String.class);
    }

    @After
    public void tearDown() throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/mongo/name/Knik");
    }

    @Test
    public void contexLoads() throws Exception {
        assertThat(this.student).isNotNull();
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mongo/name/Knik", String.class)).isNotNull();
    }

    @Test
    public void getAllStudentsShouldReturnAtLeastOneStudent() throws Exception {
        String students = this.restTemplate.getForObject("http://localhost:" + port + "/mongo",
                String.class);
        assertThat(students).contains("name");
    }
}