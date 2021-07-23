package io.bumsoft.service;

import io.bumsoft.dto.BumsoftResponse;
import org.springframework.http.ResponseEntity;

public interface BumsoftService {
    ResponseEntity<BumsoftResponse> findById(Long id);
}
