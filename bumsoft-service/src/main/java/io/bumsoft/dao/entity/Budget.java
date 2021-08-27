package io.bumsoft.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BUDGET")
public class Budget implements BumsoftEntity {
    @Id
    @Column(name = "BDGT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "budget_sequence")
    @SequenceGenerator(name = "budget_sequence", sequenceName = "BUDGET_SEQ", allocationSize = 1)
    private Long id;

    @Size(min = 2, max = 250, message = "The budget name should be between 2 and 250 characters long")
    @NotEmpty(message = "The budget name is mandatory")
    @NotBlank(message = "The budget name is mandatory")
    @NotNull(message = "The budget name is mandatory")
    @Column(name = "BDGT_NAME")
    private String name;

    @NotNull(message = "The budget amount is mandatory")
    @Min(value = 0, message = "The amount cannot be negative")
    @Column(name = "BDGT_AMOUNT")
    private Double amount;

    @Column(name = "BDGT_CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "BDGT_UPDATED_AT")
    private LocalDate updatedAt;

    @NotNull(message = "The user id is mandatory")
    @Column(name = "BDGT_USER_ID")
    private Long userId;

    @NotNull(message = "The account is mandatory for the budget")
    @Column(name = "BDGT_ACC_ID")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "BDGT_USER_ID", insertable = false, updatable = false)
    private BumsoftUser user;

    @ManyToOne
    @JoinColumn(name = "BDGT_ACC_ID", insertable = false, updatable = false)
    private Account account;
}
