package com.apiit.demo.mongo.demo.data.repository;

import com.apiit.demo.mongo.demo.data.model.Course;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import lombok.extern.log4j.Log4j2;
import org.bson.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;


@SpringBootTest
@Log4j2
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MongoClient mongoClient;

    @Value("classpath:data/db/courses.json")
    private Resource dbData;

    @BeforeEach
    public void setup() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();

        final List<Document> documents = objectMapper
                .readValue(dbData.getInputStream().readAllBytes(), new TypeReference<>() {});

        final MongoCollection<Document> collection = mongoClient.getDatabase("apiit_db")
                .getCollection("course");

        collection.insertMany(documents);
    }

    @Test
    void findCoursesOfStudent() {
        final long coursesCount = courseRepository.count();
        log.info("Found {} courses.", coursesCount);
        Assertions.assertEquals(2, coursesCount, "Expected courses count mismatch!");

        final List<Course> coursesOfStudent = courseRepository
                .findCoursesOfStudent("6199e04148d58bd7261ec176");

        Assertions.assertEquals(2, coursesOfStudent.size(), "Expected courses count of student mismatch!");
    }
}
