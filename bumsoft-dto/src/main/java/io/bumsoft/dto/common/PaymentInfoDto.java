package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfoDto implements BumsoftDto {
    private Long id;
    private Double subscriptionPrice;
    private SubscriptionDto subscription;
    private CreditCardInfoDto creditCard;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
