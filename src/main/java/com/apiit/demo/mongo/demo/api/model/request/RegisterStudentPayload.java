package com.apiit.demo.mongo.demo.api.model.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RegisterStudentPayload {
    private String name;
    private LocalDate dob;
    private List<String> enrollments = new ArrayList<>();
}
