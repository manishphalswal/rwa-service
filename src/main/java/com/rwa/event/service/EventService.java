package com.rwa.event.service;

import com.rwa.event.dao.EventDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {
    private final EventDAOWrapper eventDAOWrapper;
}
