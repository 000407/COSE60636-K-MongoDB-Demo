package com.apiit.demo.mongo.demo.data.repository;

import com.apiit.demo.mongo.demo.data.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {
    @Query("{'enrollments': {'$elemMatch': {'studentId': ?0}}}")
    List<Course> findCoursesOfStudent(String id);
}
