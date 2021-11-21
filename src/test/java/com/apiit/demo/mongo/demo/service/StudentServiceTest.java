package com.apiit.demo.mongo.demo.service;

import com.apiit.demo.mongo.demo.api.model.request.RegisterStudentPayload;
import com.apiit.demo.mongo.demo.data.model.Course;
import com.apiit.demo.mongo.demo.data.model.Student;
import com.apiit.demo.mongo.demo.data.repository.CourseRepository;
import com.apiit.demo.mongo.demo.data.repository.StudentRepository;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Log4j2
@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        log.info("Setting up the test case env...");
    }

    @AfterEach
    void tearDown() {
        log.info("Tearing down the test case env...");
    }

    @Test
    void registerNewStudentSuccessCase() {
        log.info("REGISTER_STUDENT_SUCCESS: Running tst case...");

        final Map<String, String> courses = Map.of(
                "COURSE_1", "Math",
                "COURSE_3", "Science",
                "COURSE_5", "English"
        );

        final LocalDate dob = LocalDate.of(1998, 2, 15);

        log.info("Defining mock behaviours...");
        Mockito.when(studentRepository.save(ArgumentMatchers.any(Student.class)))
                .thenAnswer(invocationOnMock -> {
                    Student studentToSave = invocationOnMock.getArgument(0);
                    studentToSave.setId("MOCK_ID_0001");
                    return studentToSave;
                });

        Mockito.when(courseRepository.findById(
                MockitoHamcrest.argThat(Matchers.is(Matchers.oneOf("COURSE_1", "COURSE_3", "COURSE_5")))
        ))
                .thenAnswer(invocationOnMock -> {
                    String courseId = invocationOnMock.getArgument(0);
                    Course course = new Course();

                    course.setId(invocationOnMock.getArgument(0));
                    course.setName(courses.get(courseId));
                    course.setEnrollments(new ArrayList<>());

                    return Optional.of(course);
                });

        Mockito.when(courseRepository.save(ArgumentMatchers.any(Course.class)))
                .thenAnswer(invocationOnMock -> {
                    Course courseToSave = invocationOnMock.getArgument(0);

                    if (courseToSave.getId() == null) {
                        courseToSave.setId("COURSE_101");
                    }
                    return courseToSave;
                });

        log.info("Preparing payload instance...");
        RegisterStudentPayload payload = new RegisterStudentPayload();

        payload.setName("John Doe");
        payload.setDob(dob);
        payload.setEnrollments(List.of("COURSE_1", "COURSE_3", "COURSE_5"));

        Student savedStudent = studentService.registerNewStudent(payload);

        //Assertions
        Assertions.assertEquals("John Doe", savedStudent.getName(), "Expected name mismatch!");
        Assertions.assertEquals(dob, savedStudent.getDob(), "Expected date of birth mismatch!");
    }

    @Test
    void registerNewStudentFailureCase() {
        //TODO: Fill this with appropriate test logic
        log.info("REGISTER_STUDENT_FAILURE: Running tst case...");
        Assertions.assertTrue(true);
    }
}
