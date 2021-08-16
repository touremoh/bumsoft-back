package io.bumsoft.service;

import io.bumsoft.dao.entity.ReferenceEntityType;
import io.bumsoft.dao.repository.ReferenceEntityTypeRepository;
import io.bumsoft.dto.common.ReferenceEntityTypeDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.ReferenceEntityTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReferenceEntityTypeService extends AbstractBumsoftService<ReferenceEntityType, ReferenceEntityTypeDto, ReferenceEntityTypeMapper, Long, ReferenceEntityTypeRepository> {

    private final ReferenceEntityTypeRepository repository;
    private final ReferenceEntityTypeMapper mapper;

    @Autowired
    public ReferenceEntityTypeService(ReferenceEntityTypeRepository repository, ReferenceEntityTypeMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeCreate(ReferenceEntityType entity) throws BumsoftException {

    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterCreate(ReferenceEntityType entity) throws BumsoftException {

    }

    /**
     * Additional process before update
     *
     * @param aLong
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeUpdate(Long aLong, ReferenceEntityType entity) throws BumsoftException {

    }

    /**
     * Additional process after update
     *
     * @param aLong
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterUpdate(Long aLong, ReferenceEntityType entity) throws BumsoftException {

    }


    public ReferenceEntityType findByName(final String name) {
        return repository.findByName(name);
    }


}
