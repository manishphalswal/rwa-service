package com.rwa.finance.service;

import com.rwa.finance.dao.FinanceDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FinanceService {
    private final FinanceDAOWrapper financeDAOWrapper;
}
