package com.email.sender.service;


import com.email.sender.exception.OperationIsStillInProgressException;

public interface FileReaderService {
    void readFile() throws OperationIsStillInProgressException;
}
