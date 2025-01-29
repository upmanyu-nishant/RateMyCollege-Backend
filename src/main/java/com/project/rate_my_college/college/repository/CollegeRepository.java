package com.project.rate_my_college.college.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.project.rate_my_college.college.model.College;

import java.util.List;

public interface CollegeRepository extends MongoRepository<College, String> {

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    List<College> findByNameContainingIgnoreCase(String name);

    @Query("{ 'location': { $regex: ?0, $options: 'i' } }")
    List<College> findByLocationContainingIgnoreCase(String location);

    @Query("{ 'name': { $regex: ?0, $options: 'i' }, 'location': { $regex: ?1, $options: 'i' } }")
    List<College> findByNameAndLocationContainingIgnoreCase(String name, String location);
}
