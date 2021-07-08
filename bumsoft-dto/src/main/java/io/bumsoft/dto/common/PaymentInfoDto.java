package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class PaymentInfoDto implements BumsoftResponse  {
    private Long id;
    private Double subscriptionPrice;
    private SubscriptionDto subscription;
    private CreditCardInfoDto creditCard;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
