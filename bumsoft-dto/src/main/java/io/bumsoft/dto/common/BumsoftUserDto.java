package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.*;

@Getter
@Setter
@Builder
public class BumsoftUserDto implements BumsoftResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
