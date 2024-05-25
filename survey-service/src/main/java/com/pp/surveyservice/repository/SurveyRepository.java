package com.pp.surveyservice.repository;

import com.pp.surveyservice.model.Option;
import com.pp.surveyservice.model.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SurveyRepository extends MongoRepository<Survey, String> {
}
