package io.bumsoft.rest;

import io.bumsoft.dto.BumsoftResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BumsoftResponseMessage> read() {
        log.info("[UserController] - read message");
        return ResponseEntity.ok(BumsoftResponseMessage.builder().code("OK - It works!").build());
    }
}
