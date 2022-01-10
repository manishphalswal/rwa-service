package com.rwa.controller.finance;

import com.rwa.service.finance.FinanceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
@Slf4j
@AllArgsConstructor
public class FinanceController {
    private final FinanceService financeService;
}
