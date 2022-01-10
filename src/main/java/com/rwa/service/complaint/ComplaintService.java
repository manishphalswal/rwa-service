package com.rwa.service.complaint;

import com.rwa.dao.complaint.ComplaintDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ComplaintService {
    private final ComplaintDAOWrapper complaintDAOWrapper;
}
