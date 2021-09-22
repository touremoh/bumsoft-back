package io.bumsoft.service;

import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.TransactionRepository;
import io.bumsoft.dao.specifications.TransactionQueryBuilder;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import io.bumsoft.dto.common.TransactionDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.ReferenceEntityTypeMapper;
import io.bumsoft.mapper.TransactionMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.ap.internal.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class TransactionService extends AbstractBumsoftService<Transaction, TransactionDto, Long> {

    private final TransactionRepository repository;
    private final ReferenceEntityTypeService referenceEntityTypeService;
    private final ReferenceEntityTypeMapper referenceMapper;

    @Autowired
    public TransactionService(
            TransactionRepository repository,
            ReferenceEntityTypeService referenceEntityTypeService,
            ReferenceEntityTypeMapper referenceMapper,
            TransactionMapper mapper,
            ValidationService<Transaction> validationService,
            TransactionQueryBuilder queryBuilder) {
        super(repository, mapper, validationService, queryBuilder);
        this.repository = repository;
        this.referenceEntityTypeService = referenceEntityTypeService;
        this.referenceMapper = referenceMapper;
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processBeforeCreate(Transaction entity) throws BumsoftException {
        Either<BumsoftException, ReferenceEntityTypeDto> ref =
                referenceEntityTypeService.findByName(entity.getTransactionType().getName());
        if (ref.isLeft()) {
            throw ref.getLeft();
        }
        entity.setProcessingDate(LocalDate.now());
        entity.setTransactionType(referenceMapper.toEntity(ref.get()));
    }

    /**
     * Additional process before update
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processBeforeUpdate(Long id, Transaction entity) throws BumsoftException {
        this.repository.findById(id).ifPresent(transaction -> {
            if (isNull(entity.getValue())) {
                entity.setValue(transaction.getValue());
            }
            if (isNull(entity.getDescription()) || Strings.isEmpty(entity.getDescription())) {
                entity.setDescription(transaction.getDescription());
            }
            if (isNull(entity.getProcessingDate())) {
                entity.setProcessingDate(transaction.getProcessingDate());
            }
            entity.setId(transaction.getId());
            entity.setTransactionType(transaction.getTransactionType());
        });
    }

    /**
     * Additional process after update
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processAfterUpdate(Long id, Transaction entity) throws BumsoftException {
        if (!id.equals(entity.getId())) {
            log.error("Failed");
            throw new BumsoftException("Failed to update resource");
        }
        // Check if transacted account is configured to AUP
        // Yes ? => check if the threshold is read
        // Yes ? => Dispatch budgets
    }
}
