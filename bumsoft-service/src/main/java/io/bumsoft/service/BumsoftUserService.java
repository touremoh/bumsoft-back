package io.bumsoft.service;

import io.bumsoft.dao.repository.BumsoftUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BumsoftUserService {
    private final BumsoftUserRepository repository;

    @Autowired
    public BumsoftUserService(BumsoftUserRepository repository) {
        this.repository = repository;
    }
}
