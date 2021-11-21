package com.apiit.demo.mongo.demo.downstream;

import com.apiit.demo.mongo.demo.api.model.request.RegisterStudentPayload;
import com.apiit.demo.mongo.demo.api.model.response.RegisteredStudent;
import com.apiit.demo.mongo.demo.data.model.Student;
import com.apiit.demo.mongo.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class StudentDownstream {
    private final StudentService studentService;

    public RegisteredStudent registerNewStudent(RegisterStudentPayload payload) {
        Student student = studentService.registerNewStudent(payload);
        return mapToRegisteredStudent(student);
    }

    private RegisteredStudent mapToRegisteredStudent(Student student) {
        log.info("Mapping entity response with ID: {}", student.getId());
        RegisteredStudent registeredStudent = new RegisteredStudent();

        registeredStudent.setId(student.getId());
        registeredStudent.setName(student.getName());
        registeredStudent.setDob(student.getDob());

        return registeredStudent;
    }
}
