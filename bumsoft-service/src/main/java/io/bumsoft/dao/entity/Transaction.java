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
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "TRANSACTION_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "TRX_VALUE")
    private Double value;

    @Column(name = "TRX_DESC")
    private String description;

    @Column(name = "TRX_DATE")
    private LocalDate processingDate;

    @Column(name = "TRX_ACC_ID")
    private Long relatedAccountId;

    @ManyToOne
    @JoinColumn(name = "TRX_ACC_ID", insertable = false, updatable = false)
    private Account relatedAccount;

    @ManyToOne
    @JoinColumn(name = "TRX_REF_TYP_ID", insertable = false, updatable = false)
    private ReferenceEntityType transactionType;
}
