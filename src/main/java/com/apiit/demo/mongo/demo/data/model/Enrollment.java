package com.apiit.demo.mongo.demo.data.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class Enrollment {
    private final String studentId;
    private final LocalDate dateStarted;
    private LocalDate dateCompleted;
    private Grade grade;

    public enum Grade {
        DISTINCTION,
        CREDIT,
        SIMPLE,
        FAIL
    }
}
