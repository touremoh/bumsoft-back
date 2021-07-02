package io.bumsoft.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "INCOME_STATEMENT")
public class IncomeStatement implements BumsoftEntity {

    @Id
    @Column(name = "INC_STMT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "income_statement_sequence")
    @SequenceGenerator(name = "income_statement_sequence", sequenceName = "INCOME_STATEMENT_SEQ")
    private Long id;

    @Column(name = "INC_STMT_NAME")
    private String name;

    @Column(name = "INC_EXPECTED_AMOUNT")
    private Double expectedAmount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INC_BS_USR_ID")
    private BumsoftUser beneficiaryUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INC_STMT_REF_TYP_ID")
    private ReferenceEntityType incomeType;

    @Column(name = "INC_CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "INC_UPDATED_AT")
    private LocalDate updatedAt;

    @Column(name = "INC_DELETED_AT")
    private LocalDate deletedAt;
}
