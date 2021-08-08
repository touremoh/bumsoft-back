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
public class CreditCardInfoDto implements BumsoftDto {
    private Long id;
    private String ownerFullName;
    private String cardNumber;
    private LocalDate expirationDate;
    private String cvvNumber;
    private SubscriptionDto subscription;
}
