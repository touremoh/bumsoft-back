package io.bumsoft.service;

import io.bumsoft.dao.entity.Transaction;
import io.bumsoft.dao.repository.TransactionRepository;
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
public class TransactionService extends AbstractBumsoftService<Transaction, TransactionDto, TransactionMapper, Long, TransactionRepository> {

    private final TransactionRepository repository;
    private final ReferenceEntityTypeService referenceEntityTypeService;
    private final ReferenceEntityTypeMapper referenceMapper;

    @Autowired
    public TransactionService(
            TransactionRepository repository,
            ReferenceEntityTypeService referenceEntityTypeService,
            ReferenceEntityTypeMapper referenceMapper,
            TransactionMapper mapper,
            ValidationService<Transaction> validationService) {
        super(repository, mapper, validationService);
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
    void processBeforeCreate(Transaction entity) throws BumsoftException {
        entity.setProcessingDate(LocalDate.now());
        Either<BumsoftException, ReferenceEntityTypeDto> ref = referenceEntityTypeService.findByName(entity.getTransactionType().getName());
        if (ref.isLeft()) {
            throw ref.getLeft();
        }
        entity.setTransactionType(referenceMapper.toEntity(ref.get()));
    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterCreate(Transaction entity) throws BumsoftException {

    }

    /**
     * Additional process before update
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeUpdate(Long id, Transaction entity) throws BumsoftException {
        this.repository.findById(id).ifPresent(transaction -> {
            entity.setId(transaction.getId());
            entity.setTransactionType(transaction.getTransactionType());

            if (isNull(entity.getValue())) {
                entity.setValue(transaction.getValue());
            }
            if (isNull(entity.getDescription()) || Strings.isEmpty(entity.getDescription())) {
                entity.setDescription(transaction.getDescription());
            }
            if (isNull(entity.getProcessingDate())) {
                entity.setProcessingDate(transaction.getProcessingDate());
            }
        });
    }

    /**
     * Additional process after update
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterUpdate(Long id, Transaction entity) throws BumsoftException {
        if (!id.equals(entity.getId())) {
            throw new BumsoftException("Failed to update resource");
        }
    }
}
