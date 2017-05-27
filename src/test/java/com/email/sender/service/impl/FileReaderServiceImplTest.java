package com.email.sender.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileReaderServiceImplTest {

    @Autowired
    private FileReaderServiceImpl service;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    String filePath;

    @Before
    public void setUp() throws Exception {
        filePath = "testLight.csv";
        service = new FileReaderServiceImpl(kafkaTemplate);
    }


    @Test(expected = IOException.class)
    public void shouldThrowExceptionWhenFilePathIsWrong() throws Exception {
        service.readFile("/wrongPath.csv");
    }

}