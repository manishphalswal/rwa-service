package com.rwa.controller.complaint;

import com.rwa.service.complaint.ComplaintService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/complaints")
@Slf4j
@AllArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;
}
