package io.bumsoft.service;

import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.TransactionRepository;
import io.bumsoft.dto.common.TransactionDto;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.bumsoft.mapper.TransactionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class TransactionService extends AbstractBumsoftService<Transaction, TransactionRepository, TransactionDto> {

    private final TransactionRepository repository;
    private final ReferenceEntityTypeService referenceEntityTypeService;
    private final AbstractObjectsMapper<Transaction, TransactionDto> mapper;

    @Autowired
    public TransactionService(TransactionRepository repository, ReferenceEntityTypeService referenceEntityTypeService, TransactionMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.referenceEntityTypeService = referenceEntityTypeService;
        this.mapper = mapper;
    }

    /**
     * This method is used to process the object before persisting it
     *
     * @param object to be process before persistence
     * @return the entity to be persisted
     */
    @Override
    public Transaction processBeforeCreate(TransactionDto object) {
        object.setProcessingDate(LocalDate.now());
        ReferenceEntityType transType = referenceEntityTypeService.findByName(object.getTransactionType());

        Transaction transaction = mapper.toEntity(object);
        transaction.setTransactionType(transType);

        return transaction;
    }

    /**
     * Process after the object is persisted
     *
     * @param entity persisted object
     * @return void
     */
    @Override
    public TransactionDto processAfterCreate(Transaction entity) {
        return mapper.toDto(entity);
    }
}
