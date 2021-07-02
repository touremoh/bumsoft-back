package io.bumsoft.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ACCOUNT")
public class Account implements BumsoftEntity {

    @Id
    @Column(name = "ACC_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    @SequenceGenerator(name = "account_sequence", sequenceName = "ACCOUNT_SEQ")
    private Long id;

    @Column(name = "ACC_NAME")
    private String name;

    @Column(name = "ACC_DESC")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ACC_BS_USR_ID")
    private BumsoftUser user;

    @ManyToOne
    @JoinColumn(name = "ACC_REF_TYPE_ID")
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
