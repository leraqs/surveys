package com.pp.surveyservice.repository;

import com.pp.surveyservice.model.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyRepository extends MongoRepository<Survey, String> {
}

