package com.apiit.demo.mongo.demo.api;

import com.apiit.demo.mongo.demo.api.model.request.RegisterStudentPayload;
import com.apiit.demo.mongo.demo.api.model.response.RegisteredStudent;
import com.apiit.demo.mongo.demo.downstream.StudentDownstream;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/student")
public class StudentController {
    private final StudentDownstream studentDownstream;

    @PostMapping("/register")
    public ResponseEntity<RegisteredStudent> registerNewStudent(@RequestBody RegisterStudentPayload payload) {
        log.info("Request received to register new student: {}", payload.getName());
        return ResponseEntity.ok()
                .body(studentDownstream.registerNewStudent(payload));
    }
}
