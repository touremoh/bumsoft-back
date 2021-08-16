package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDto implements BumsoftDto {
    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isAutomaticallyRenewable;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private ReferenceEntityTypeDto subscriptionType;
    private List<CreditCardInfoDto> creditCards;
}
