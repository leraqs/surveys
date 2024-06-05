package com.pp.surveyservice.repository;


import com.pp.surveyservice.model.UserResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserResponseRepository extends MongoRepository<UserResponse, String> {
    List<UserResponse> findBySurveyId(String surveyId);
}
