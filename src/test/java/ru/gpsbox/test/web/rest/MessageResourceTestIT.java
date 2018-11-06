package ru.gpsbox.test.web.rest;

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
import ru.gpsbox.test.MongoRestApplication;
import ru.gpsbox.test.domain.mongo.Message;

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
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/mongo/id/" + messageTestId, String.class)).isEqualTo("[]");
    }
}