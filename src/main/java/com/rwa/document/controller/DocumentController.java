package com.rwa.document.controller;

import com.rwa.document.service.DocumentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents")
@Slf4j
@AllArgsConstructor
public class DocumentController {
    private final DocumentService documentService;
}
