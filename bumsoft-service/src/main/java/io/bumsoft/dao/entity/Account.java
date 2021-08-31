package io.bumsoft.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ACCOUNT", schema = "bumsoftdb")
public class Account implements BumsoftEntity {

    @Id
    @Column(name = "ACC_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "ACC_NUM")
    private String accountNumber;

    @NotNull(message = "The account name is mandatory")
    @Column(name = "ACC_NAME")
    private String name;

    @Column(name = "ACC_DESC")
    private String description;

    @NotNull(message = "The user id is mandatory")
    @Column(name = "ACC_BS_USR_ID")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "ACC_BS_USR_ID", insertable = false, updatable = false)
    private BumsoftUser user;

    @ManyToOne
    @JoinColumn(name = "ACC_TYPE")
    private ReferenceEntityType accountType;

    @Column(name = "ACC_CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "ACC_UPDATED_AT")
    private LocalDate updatedAt;

    @Column(name = "ACC_DELETED_AT")
    private LocalDate deletedAt;

    @OneToMany(mappedBy = "relatedAccount", fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
