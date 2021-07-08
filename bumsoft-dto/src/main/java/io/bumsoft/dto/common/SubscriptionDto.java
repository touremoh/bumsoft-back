package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class SubscriptionDto implements BumsoftResponse  {
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
