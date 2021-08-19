package io.bumsoft.dao.entity;

import lombok.*;

import javax.persistence.*;
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

    @Column(name = "BDGT_NAME")
    private String name;

    @Column(name = "BDGT_AMOUNT")
    private Double amount;

    @Column(name = "BDGT_CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "BDGT_UPDATED_AT")
    private LocalDate updatedAt;

    @Column(name = "BDGT_USER_ID")
    private Long userId;

    @Column(name = "BDGT_ACC_ID")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "BDGT_USER_ID", insertable = false, updatable = false)
    private BumsoftUser user;

    @ManyToOne
    @JoinColumn(name = "BDGT_ACC_ID", insertable = false, updatable = false)
    private Account account;
}
