package io.bumsoft.service;

import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.TransactionRepository;
import io.bumsoft.dto.common.IncomeStatementDto;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository repository;

    @Autowired
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }
}
