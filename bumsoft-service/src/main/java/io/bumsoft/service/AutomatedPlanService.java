package io.bumsoft.service;


import io.bumsoft.dao.entity.Account;
import io.bumsoft.dao.entity.AutomatedPlan;
import io.bumsoft.dao.repository.AutomatedPlanRepository;
import io.bumsoft.dao.specifications.AutomatedPlanQueryBuilder;
import io.bumsoft.dto.common.AutomatedPlanDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.AutomatedPlanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class AutomatedPlanService extends AbstractBumsoftService<AutomatedPlan, AutomatedPlanDto, Long> {

    private final AutomatedPlanRepository repository;


    public AutomatedPlanService(
            AutomatedPlanRepository repository,
            AutomatedPlanMapper mapper,
            ValidationService<AutomatedPlan> validationService,
            AutomatedPlanQueryBuilder queryBuilder) {
        super(repository, mapper, validationService, queryBuilder);
        this.repository = repository;
    }

    /**
     * Additional process before persisting the entity
     *
     * @param entity plan before the create operation
     */
    @Override
    public void processBeforeCreate(AutomatedPlan entity) {
        log.info("Automated plan service before create");
        entity.setCreatedAt(LocalDate.now());
    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity newly created plan
     * @throws BumsoftException exception to throw when an error occurs
     */
    @Override
    public void processAfterCreate(AutomatedPlan entity) throws BumsoftException {
        if (!this.repository.existsById(entity.getId())) {
            log.error("Unable to create automated plan");
            throw new BumsoftException("The automated plan creation failed");
        }
    }

    /**
     * Additional process before update
     * @param id id of the plan to update
     * @param entity new plan data
     */
    @Override
    public void processBeforeUpdate(Long id, AutomatedPlan entity) {
        log.info("Process before automated plan update");
        this.repository.findById(id).ifPresent(aup -> {
            patch(entity, aup, AutomatedPlan::getThreshold, (AutomatedPlan plan, Object v) -> plan.setThreshold((Double) v));
            patch(entity, aup, AutomatedPlan::getIsActive, (AutomatedPlan plan, Object v) -> plan.setIsActive((Boolean) v));
            patch(entity, aup, AutomatedPlan::getUserId, (AutomatedPlan plan, Object v) -> plan.setUserId((Long) v));
            patch(entity, aup, AutomatedPlan::getAccount, (AutomatedPlan plan, Object v) -> plan.setAccount((Account) v));
            entity.setId(aup.getId());
            entity.setCreatedAt(aup.getCreatedAt());
            entity.setUpdatedAt(LocalDate.now());
        });
    }

    /**
     * Additional process after update
     *
     * @param id id of the plan after the update
     * @param entity updated plan
     * @throws BumsoftException errors to propagate when something went wrong
     */
    @Override
    public void processAfterUpdate(Long id, AutomatedPlan entity) throws BumsoftException {
        log.info("Process after AUP update");
        if (!id.equals(entity.getId())) {
            log.error("AUP update failed");
            throw new BumsoftException("Automated plan update failed");
        }
    }
}
