package io.bumsoft.rest;

import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.TransactionRepository;
import io.bumsoft.dto.common.TransactionDto;
import io.bumsoft.service.AbstractBumsoftService;
import io.bumsoft.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController extends AbstractBumsoftController<Transaction, TransactionDto, TransactionRepository, TransactionService> {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        super(transactionService);
        this.transactionService=transactionService;
    }
}
