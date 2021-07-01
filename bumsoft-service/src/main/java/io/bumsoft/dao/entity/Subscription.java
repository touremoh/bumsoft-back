package io.bumsoft.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "SUBSCRIPTION")
public class Subscription implements BumsoftEntity {
    @Id
    @Column(name = "SUBS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscription_sequence")
    @SequenceGenerator(name = "subscription_sequence", sequenceName = "SUBSCRIPTION_SEQ")
    private Long id;

    @Column(name = "SUBS_CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "SUBS_UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "SUBS_DELETED_AT")
    private LocalDateTime deletedAt;

    @Column(name = "SUBS_AUTO_RENEWAL")
    private Boolean isAutomaticallyRenewable;

    @Column(name = "SUBS_START_DT")
    private LocalDate startDate;

    @Column(name = "SUBS_END_DT")
    private LocalDate endDate;

    @Column(name = "SUBS_PRICE")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "SUBS_REF_TYP_ID")
    private ReferenceEntityType subscriptionType;

    @ManyToOne
    @JoinColumn(name = "SUBS_BS_USR_ID")
    private BumsoftUser subscriber;

    // List of all credit cards attached to the subscription
    @OneToMany(mappedBy = "subscription")
    private List<CreditCardInfo> creditCards;
}
