package com.email.sender.utility;

import com.email.sender.type.EmailType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;


public class EmailDeserializer implements Deserializer<EmailType> {

    /**
     * Custom Deserialization in order to send Object to topic
     */
    @Override
    public EmailType deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        EmailType emailType = null;
        try {
            emailType = mapper.readValue(arg1, EmailType.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return emailType;
    }
    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {

    }
}
