package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CreditCardInfoDto implements BumsoftResponse  {
    private Long id;
    private String ownerFullName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvvNumber;
    private SubscriptionDto subscription;
}
