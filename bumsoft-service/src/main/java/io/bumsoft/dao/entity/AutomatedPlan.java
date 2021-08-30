package io.bumsoft.dao.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AUTOMATED_PLAN", schema = "bumsoftdb")
public class AutomatedPlan implements BumsoftEntity {

    @Id
    @Column(name = "AUP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "automated_plan_sequence")
    @SequenceGenerator(name = "automated_plan_sequence", sequenceName = "AUTOMATED_PLAN_SEQ", allocationSize = 1)
    private Long id;

    @NotNull(message = "The threshold is mandatory")
    @Min(value = 0, message = "Negative amount not allowed")
    @Column(name = "AUP_THRESHOLD")
    private Double threshold;

    @Column(name = "AUP_ACTIVE")
    private Boolean isActive;

    @Column(name = "AUP_CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "AUP_UPDATED_AT")
    private LocalDate updatedAt;

    @NotNull(message = "The user is mandatory")
    @Column(name = "AUP_USER_ID")
    private Long userId;

    @NotNull(message = "The account is mandatory")
    @Column(name = "AUP_ACC_ID")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "AUP_USER_ID", insertable = false, updatable = false)
    private BumsoftUser user;

    @ManyToOne
    @JoinColumn(name = "AUP_ACC_ID", insertable = false, updatable = false)
    private Account account;
}
