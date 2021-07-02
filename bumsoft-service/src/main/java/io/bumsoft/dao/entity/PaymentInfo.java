package io.bumsoft.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PAYMENT_INFO")
public class PaymentInfo implements BumsoftEntity {
    @Id
    @Column(name = "PI_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_info_sequence")
    @SequenceGenerator(name = "payment_info_sequence", sequenceName = "PAYMENT_INFO_SEQ")
    private Long id;

    @Column(name = "PI_SUBSCRIPTION_PRICE")
    private Double subscriptionPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PI_SUBS_ID")
    private Subscription subscription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PI_CC_ID")
    private CreditCardInfo creditCard;

    @Column(name = "PI_CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "PI_UPDATED_AT")
    private LocalDate updatedAt;

    @Column(name = "PI_DELETED_AT")
    private LocalDate deletedAt;
}
