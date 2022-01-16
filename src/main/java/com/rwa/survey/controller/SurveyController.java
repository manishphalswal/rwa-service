package com.rwa.survey.controller;

import com.rwa.survey.service.SurveyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey")
@Slf4j
@AllArgsConstructor
public class SurveyController {
    private final SurveyService surveyService;
}
