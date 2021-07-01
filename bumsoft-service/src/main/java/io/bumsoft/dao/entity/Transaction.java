package io.bumsoft.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
public class Transaction implements BumsoftEntity {
    @Id
    @Column(name = "TRX_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "TRANSACTION_SEQ")
    private Long id;

    @Column(name = "TRX_VALUE")
    private Double value;

    @Column(name = "TRX_DESC")
    private String description;

    @Column(name = "TRX_DATE")
    private LocalDate processingDate;

    @ManyToOne
    @JoinColumn(name = "TRX_ACC_ID")
    private Account relatedAccount;

    @ManyToOne
    @JoinColumn(name = "TRX_INC_STMT_ID")
    private IncomeStatement statement;

    @ManyToOne
    @JoinColumn(name = "TRX_REF_TYP_ID")
    private ReferenceEntityType transactionType;
}
