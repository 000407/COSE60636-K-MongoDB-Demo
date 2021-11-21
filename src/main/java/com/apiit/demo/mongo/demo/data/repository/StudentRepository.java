package com.apiit.demo.mongo.demo.data.repository;

import com.apiit.demo.mongo.demo.data.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
}
