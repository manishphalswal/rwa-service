package com.rwa.service.survey;

import com.rwa.dao.survey.SurveyDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SurveyService {
    private final SurveyDAOWrapper surveyDAOWrapper;
}
