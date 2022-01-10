package com.rwa.service.document;

import com.rwa.dao.document.DocumentDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DocumentService {
    private final DocumentDAOWrapper documentDAOWrapper;
}
