package com.apiit.demo.mongo.demo.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisteredStudent {
    private String id;
    private String name;
    private LocalDate dob;
}
