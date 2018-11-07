package ru.gpsbox.test.web.rest;

import com.google.common.collect.Iterables;
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
import ru.gpsbox.test.MongoRestApplication;
import ru.gpsbox.test.domain.mongo.Message;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MongoRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageResourceTestIT {


    private HttpHeaders httpHeaders = new HttpHeaders();

    private Message message;

    @Autowired
    private MessageResource messageResource;
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    private String messageName = "student";
    private String messageText = "TestMessageText";
    private String messageTestId = "BBCCDDDDDD";


    @Before
    public void setUp() throws Exception {
        this.message = new Message(messageTestId, messageName, messageText);
        this.insertTestMessage();
        this.checkTestMessageInDb();
    }

    private void insertTestMessage() {
        this.restTemplate.postForObject(
                "http://localhost:" + port + "/message",
                this.message,
                String.class);
    }

    @Test
    public void list() {
    }

    @Test
    public void getOneMessage() {
    }


    @After
    public void tearDown() throws Exception {
        this.restTemplate.delete("http://localhost:" + port + "/message/id/" + messageTestId);
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/message/id/" + messageTestId, String.class)).isEqualTo("[]");
    }

    public void checkTestMessageInDb() {
        List<Message> actual = this.restTemplate.exchange("http://localhost:" + port + "/message/name/" + messageName,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Message>>() {
                }).getBody();

        assert actual != null;
        Message msg = Iterables.tryFind(actual,
                testStudent -> {
                    assert testStudent != null;
                    return messageName.equals(testStudent.getName());
                }).orNull();
        assert msg != null;
        this.message = msg;
    }

}