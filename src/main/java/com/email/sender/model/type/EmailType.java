package com.email.sender.model.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailType implements Serializable{
    private static final long serialVersionUID = -912983119584018825L;

    private String address;
    private String name;
    private String surname;

}
