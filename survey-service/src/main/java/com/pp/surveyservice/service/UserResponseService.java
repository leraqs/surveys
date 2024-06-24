package com.pp.surveyservice.service;

import com.pp.surveyservice.model.UserResponse;
import com.pp.surveyservice.repository.UserResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserResponseService {

    private final UserResponseRepository userResponseRepository;

    public UserResponse saveUserResponse(UserResponse userResponse) {
        return userResponseRepository.save(userResponse);
    }

    public List<UserResponse> getUserResponsesByCriteria(String surveyId, String criteria) {

        List<UserResponse> responses = userResponseRepository.findBySurveyId(surveyId);

        return responses.stream()
                .filter(response -> response.getGroupingAnswers().stream()
                        .anyMatch(answer -> answer.getSelectedOptions().stream()
                                .anyMatch(option -> option.getText().equals(criteria))))
                .collect(Collectors.toList());
    }
}
