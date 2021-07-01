package io.bumsoft.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class AccountDto implements BumsoftDto {
    private Long id;
    private String name;
    private String description;
    private BumsoftUserDto user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private List<TransactionDto> transactions;
}
