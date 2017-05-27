package com.email.sender.service.impl;

import au.com.bytecode.opencsv.CSVReader;
import com.email.sender.exception.OperationIsStillInProgressException;
import com.email.sender.model.type.EmailType;
import com.email.sender.service.FileReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
@Slf4j
@Async
class FileReaderServiceImpl implements FileReaderService {

    private KafkaTemplate kafkaTemplate;

    private static final String EMAIL_TOPIC_NAME = "re";

    @Value("${file.directory}")
    private String filePath;

    private AtomicInteger operationCount;

    @PostConstruct
    void init() {operationCount = new AtomicInteger(0);}

    @Autowired
    FileReaderServiceImpl(KafkaTemplate kafkaTemplate) {this.kafkaTemplate = kafkaTemplate;}

    /**
     *   Reads file line by line
     * @throws Exception Thrown if an error occurs while reading data from the  source stream.
     * @throws java.io.IOException Thrown if file not found
     */
    void readFile(String filePath) throws IOException {

        CSVReader csvReader;

        /**
         * Reading the CSV File
         * Delimiter is semicolon
         * Start reading from line 1
         */
        csvReader = new CSVReader(new FileReader(filePath), ';');

        String[] emailData;

        int TOTAL_COLUMN_VALUE = 3;
        while ((emailData = csvReader.readNext()) != null && emailData.length == TOTAL_COLUMN_VALUE) {
            EmailType emailType = EmailType.builder().address(emailData[0])
                    .name(emailData[1]).surname(emailData[2]).build();

            kafkaTemplate.send(EMAIL_TOPIC_NAME, emailType);
        }
        operationCount.decrementAndGet();
    }

    /**
     * Trigger read file and send email
     * @throws com.email.sender.exception.OperationIsStillInProgressException Thrown while operation is still in progress
     */
    @Override
    public void readFile() throws OperationIsStillInProgressException {

        if (operationCount.intValue() == 0) {
            operationCount.incrementAndGet();
            Thread t = new Thread() {
                public void run() {
                    try {
                        readFile(filePath);
                    } catch (IOException e) {
                        log.error("Error While Reading File");
                    }
                }
            };
            t.start();
        } else {
            throw new OperationIsStillInProgressException("Operation Is Running");
        }
    }

}
