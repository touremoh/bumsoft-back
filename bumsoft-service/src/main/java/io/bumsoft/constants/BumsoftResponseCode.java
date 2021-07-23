package io.bumsoft.constants;

import lombok.Getter;

@Getter
public enum BumsoftResponseCode {
     RESOURCE_NOT_FOUND(400, "Resource not found");

     private final Integer code;
     private final String description;

     BumsoftResponseCode(Integer code, String description) {
          this.code = code;
          this.description = description;
     }
}
