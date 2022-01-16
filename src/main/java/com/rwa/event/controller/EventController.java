package com.rwa.event.controller;

import com.rwa.event.service.EventService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@Slf4j
@AllArgsConstructor
public class EventController {
    private final EventService eventService;
}
