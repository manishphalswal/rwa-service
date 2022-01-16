package com.rwa.document.service;

import com.rwa.document.dao.DocumentDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentDAOWrapper documentDAOWrapper;
}
