package com.pp.surveyservice.repository;

import com.pp.surveyservice.model.Option;
import com.pp.surveyservice.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionRepository extends MongoRepository<Question, String> {
    List<Option> findOptionsBySurveyIdAAnd(String surveyId, String questionId);
}
