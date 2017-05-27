package com.email.sender.utility;


import com.email.sender.model.type.EmailType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class EmailSerializer implements Serializer<EmailType> {
    @Override
    public byte[] serialize(String arg1, EmailType emailType) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(emailType).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;    }

    @Override
    public void close() {

    }
    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }
}
