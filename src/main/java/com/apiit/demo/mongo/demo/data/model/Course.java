package com.apiit.demo.mongo.demo.data.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    private String id;
    private String name;
    private List<Enrollment> enrollments;
}
