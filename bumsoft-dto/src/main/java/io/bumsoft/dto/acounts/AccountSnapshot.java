package io.bumsoft.dto.acounts;

import io.bumsoft.dto.BumsoftDto;
import io.bumsoft.dto.common.AccountDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountSnapshot implements BumsoftDto {
    private Double accountBalance;
    private AccountDto accountInfo;
}
