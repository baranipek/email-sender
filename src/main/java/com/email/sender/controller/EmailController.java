package com.email.sender.controller;


import com.email.sender.exception.OperationIsStillInProgressException;
import com.email.sender.service.FileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value="/email")
public class EmailController  {

    private FileReaderService fileReaderService;

    @Autowired
    public EmailController(FileReaderService fileReaderService) {this.fileReaderService = fileReaderService;}

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void read() throws IOException, OperationIsStillInProgressException {
        this.fileReaderService.readFile();
    }
}
