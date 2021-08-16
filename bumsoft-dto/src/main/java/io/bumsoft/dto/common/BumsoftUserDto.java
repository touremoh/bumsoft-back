package io.bumsoft.dto.common;

import io.bumsoft.dto.BumsoftDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BumsoftUserDto implements BumsoftDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
