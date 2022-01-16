package com.rwa.survey.service;

import com.rwa.survey.dao.SurveyDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SurveyService {
    private final SurveyDAOWrapper surveyDAOWrapper;
}
