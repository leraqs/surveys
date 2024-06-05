package com.pp.surveyservice.controller;

import com.pp.surveyservice.model.UserResponse;
import com.pp.surveyservice.service.UserResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-response")
public class UserResponseController {

    private final UserResponseService userResponseService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponse saveUserResponse(@RequestBody UserResponse userResponse) {
        return userResponseService.saveUserResponse(userResponse);
    }

    @GetMapping
    public List<UserResponse> getUserResponsesByCriteria(
            @RequestParam String surveyId,
            @RequestParam String criteria) {
        return userResponseService.getUserResponsesByCriteria(surveyId, criteria);
    }
}
