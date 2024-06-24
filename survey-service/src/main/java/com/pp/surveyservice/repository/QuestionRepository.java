package com.pp.surveyservice.repository;

import com.pp.surveyservice.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {
}
