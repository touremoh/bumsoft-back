package io.bumsoft.service;

import io.bumsoft.dao.entity.BumsoftUser;
import io.bumsoft.dao.repository.BumsoftUserRepository;
import io.bumsoft.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BumsoftUserService {
    private final BumsoftUserRepository repository;

    @Autowired
    public BumsoftUserService(BumsoftUserRepository repository) {
        this.repository = repository;
    }

    public BumsoftUser findById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
}
