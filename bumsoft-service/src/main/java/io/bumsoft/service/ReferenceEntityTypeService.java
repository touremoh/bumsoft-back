package io.bumsoft.service;

import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dao.repository.ReferenceEntityTypeRepository;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.ReferenceEntityTypeMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class ReferenceEntityTypeService extends AbstractBumsoftService<ReferenceEntityType, ReferenceEntityTypeDto, ReferenceEntityTypeMapper, Long, ReferenceEntityTypeRepository> {

    private final ReferenceEntityTypeRepository repository;
    private final ReferenceEntityTypeMapper mapper;

    @Autowired
    public ReferenceEntityTypeService(ReferenceEntityTypeRepository repository, ReferenceEntityTypeMapper mapper, ValidationService<ReferenceEntityType> validationService) {
        super(repository, mapper, validationService);
        this.repository = repository;
        this.mapper = mapper;
    }

    public Either<BumsoftException, ReferenceEntityTypeDto> findByName(final String name) {
        return ofNullable(this.repository.findByName(name))
                .<Either<BumsoftException, ReferenceEntityTypeDto>>map(e -> Either.right(mapper.toDto(e)))
                .orElseGet(() -> Either.left(new BumsoftException("No reference found for name: " + name)));

    }
}
