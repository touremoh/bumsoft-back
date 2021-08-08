package io.bumsoft.service;

import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dao.repository.ReferenceEntityTypeRepository;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import io.bumsoft.mapper.AbstractObjectsMapper;
import io.bumsoft.mapper.ReferenceEntityTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReferenceEntityTypeService extends AbstractBumsoftService<ReferenceEntityType, ReferenceEntityTypeRepository, ReferenceEntityTypeDto> {

    private final ReferenceEntityTypeRepository repository;
    private final AbstractObjectsMapper<ReferenceEntityType, ReferenceEntityTypeDto> mapper;

    @Autowired
    public ReferenceEntityTypeService(ReferenceEntityTypeRepository repository, ReferenceEntityTypeMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public ReferenceEntityType findByName(final String name) {
        return repository.findByName(name);
    }

    /**
     * This method is used to process the object before persisting it
     *
     * @param object to be process before persistence
     * @return the entity to be persisted
     */
    @Override
    public ReferenceEntityType processBeforeCreate(ReferenceEntityTypeDto object) {
        return null;
    }

    /**
     * Process after the object is persisted
     *
     * @param entity persisted object
     * @return void
     */
    @Override
    public ReferenceEntityTypeDto processAfterCreate(ReferenceEntityType entity) {
        return null;
    }
}
