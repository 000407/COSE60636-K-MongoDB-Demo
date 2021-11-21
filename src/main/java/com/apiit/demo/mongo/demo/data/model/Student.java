package com.apiit.demo.mongo.demo.data.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document
public class Student {
    @Id private String id;
    private String name;
    private LocalDate dob;
}
