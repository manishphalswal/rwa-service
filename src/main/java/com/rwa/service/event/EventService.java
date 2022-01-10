package com.rwa.service.event;

import com.rwa.dao.event.EventDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventService {
    private final EventDAOWrapper eventDAOWrapper;
}
