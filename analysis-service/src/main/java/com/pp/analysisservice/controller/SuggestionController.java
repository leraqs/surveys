package com.pp.analysisservice.controller;

import com.pp.analysisservice.model.Suggestion;
import com.pp.analysisservice.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/suggestions")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @GetMapping("/user/{userId}")
    public List<Suggestion> getSuggestionsByUserId(@PathVariable String userId,
                                                   @RequestParam(defaultValue = "5") Integer suggestionsNumber) {
        return suggestionService.generateSuggestions(userId, suggestionsNumber);
    }
}

