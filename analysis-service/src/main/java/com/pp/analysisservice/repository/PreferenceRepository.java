package com.pp.analysisservice.repository;

import com.pp.analysisservice.model.Preference;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PreferenceRepository extends MongoRepository<Preference, String> {
    List<Preference> findByUserId(String userId);

    List<Preference> findBySurveyId(String surveyId);
}
