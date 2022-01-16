package com.rwa.complaint.service;

import com.rwa.complaint.dao.ComplaintDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComplaintService {
    private final ComplaintDAOWrapper complaintDAOWrapper;
}
