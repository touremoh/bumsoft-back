package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TransactionDto implements BumsoftResponse  {

    // Unique identifier of the transaction
    private Long id;

    // The amount of the transaction
    private Double value;

    // The transaction processing date
    private LocalDate processingDate;

    // Tell if the transaction is a Debit or a Credit
    private String transactionType;

    // Description
    private String description;
}
