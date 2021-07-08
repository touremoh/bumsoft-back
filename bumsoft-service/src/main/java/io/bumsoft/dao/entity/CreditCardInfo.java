package io.bumsoft.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CREDIT_CARD_INFO")
public class CreditCardInfo implements BumsoftEntity {
    @Id
    @Column(name = "CC_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_card_sequence")
    @SequenceGenerator(name = "credit_card_sequence", sequenceName = "CREDIT_CARD_SEQ")
    private Long id;

    @Column(name = "CC_OWNER_FULL_NAME")
    private String ownerFullName;

    @Column(name = "CC_NUMBER")
    private String cardNumber;

    @Column(name = "CC_EXPIRATION_DT")
    private LocalDate expirationDate;

    @Column(name = "CC_CVV_NUMBER")
    private String cvvNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CC_BS_USR_ID")
    private BumsoftUser owner;

    @Column(name = "CC_CREATED_AT")
    private LocalDate createdAt;

    @Column(name = "CC_UPDATED_AT")
    private LocalDate updatedAt;

    @Column(name = "CC_DELETED_AT")
    private LocalDate deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CC_SUBS_ID")
    private Subscription subscription;

    // List of payments perform with the credit card
    @OneToMany(mappedBy = "creditCard", fetch = FetchType.LAZY)
    private List<PaymentInfo> payments;
}
