package io.bumsoft.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TransactionDto {

    // Unique identifier of the transaction
    private Long id;

    // The amount of the transaction
    private Double transactionValue;

    // The transaction processing date
    private LocalDate processingDate;

    // Tell if the transaction is a Debit or a Credit
    private String transactionType;

    // Related account
    private Long accountId;

    // Income source
    private String incomeSource;

    // Description
    private String description;
}
