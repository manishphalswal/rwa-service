package com.rwa.service.finance;

import com.rwa.dao.finance.FinanceDAOWrapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FinanceService {
    private final FinanceDAOWrapper financeDAOWrapper;
}
