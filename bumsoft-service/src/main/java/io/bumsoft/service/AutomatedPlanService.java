package io.bumsoft.service;


import io.bumsoft.dao.entity.AutomatedPlan;
import io.bumsoft.dao.repository.AutomatedPlanRepository;
import io.bumsoft.dto.common.AutomatedPlanDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.AutomatedPlanMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AutomatedPlanService extends AbstractBumsoftService<AutomatedPlan, AutomatedPlanDto, AutomatedPlanMapper, Long, AutomatedPlanRepository> {

    private final AutomatedPlanRepository repository;
    private final AutomatedPlanMapper mapper;

    protected AutomatedPlanService(AutomatedPlanRepository repository, AutomatedPlanMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    public Either<BumsoftException, List<AutomatedPlanDto>> findByUserId(Long userId) {
        List<AutomatedPlanDto> plans = this.repository.findByUserIdAndIsActiveIsTrue(userId).stream().map(mapper::toDto).collect(Collectors.toList());
        return plans.isEmpty() ? Either.left(new BumsoftException("No plan found for user " + userId)) : Either.right(plans);
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeCreate(AutomatedPlan entity) throws BumsoftException {

    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterCreate(AutomatedPlan entity) throws BumsoftException {

    }

    /**
     * Additional process before update
     *
     * @param aLong
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processBeforeUpdate(Long aLong, AutomatedPlan entity) throws BumsoftException {

    }

    /**
     * Additional process after update
     *
     * @param aLong
     * @param entity
     * @throws BumsoftException
     */
    @Override
    void processAfterUpdate(Long aLong, AutomatedPlan entity) throws BumsoftException {

    }
}
