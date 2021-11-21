package com.apiit.demo.mongo.demo.service;

import com.apiit.demo.mongo.demo.api.model.request.RegisterStudentPayload;
import com.apiit.demo.mongo.demo.data.model.Course;
import com.apiit.demo.mongo.demo.data.model.Enrollment;
import com.apiit.demo.mongo.demo.data.model.Student;
import com.apiit.demo.mongo.demo.data.repository.CourseRepository;
import com.apiit.demo.mongo.demo.data.repository.StudentRepository;
import com.apiit.demo.mongo.demo.exception.CourseNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Log4j2
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public Student registerNewStudent(RegisterStudentPayload payload) {
        log.info("Generating entity from payload: {}", payload);
        Student student = new Student();

        student.setDob(payload.getDob());
        student.setName(payload.getName());

        log.info("Saving student data");
        Student savedStudent = studentRepository.save(student);

        //TODO: Eliminate DB access in a loop
        for (String courseId : payload.getEnrollments()) {
            Course course = courseRepository.findById(courseId) // Retrieval
                    .orElseThrow(() -> {
                        final String message = log.getMessageFactory()
                            .newMessage("No course was found for ID: {}", courseId)
                            .getFormattedMessage();

                        log.error(message);

                        return new CourseNotFoundException(message);
                    });

            course.getEnrollments().add(new Enrollment(savedStudent.getId(), LocalDate.now())); // Update

            courseRepository.save(course); // Persisting
        }

        return savedStudent;
    }
}
