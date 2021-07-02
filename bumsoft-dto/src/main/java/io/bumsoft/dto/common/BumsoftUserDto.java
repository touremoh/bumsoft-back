package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
