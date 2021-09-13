package io.bumsoft.service;


import io.bumsoft.dao.entity.AutomatedPlan;
import io.bumsoft.dao.repository.AutomatedPlanRepository;
import io.bumsoft.dto.common.AutomatedPlanDto;
import io.bumsoft.exception.BumsoftException;
import io.bumsoft.mapper.AutomatedPlanMapper;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class AutomatedPlanService extends AbstractBumsoftService<AutomatedPlan, AutomatedPlanDto, AutomatedPlanMapper, Long, AutomatedPlanRepository> {

    private final AutomatedPlanRepository repository;
    private final AutomatedPlanMapper mapper;


    public AutomatedPlanService(AutomatedPlanRepository repository, AutomatedPlanMapper mapper, ValidationService<AutomatedPlan> validationService) {
        super(repository, mapper, validationService);
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
    public void processBeforeCreate(AutomatedPlan entity) throws BumsoftException {
        log.info("Automated plan service before create");
        entity.setCreatedAt(LocalDate.now());
    }

    /**
     * Additional process after the object has been persisted
     *
     * @param entity
     * @throws BumsoftException
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
     * @param id
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processBeforeUpdate(Long id, AutomatedPlan entity) throws BumsoftException {
        log.info("Process before automated plan update");
        this.repository.findById(id).ifPresent(aup -> {
            if (isNull(entity.getThreshold())) {
                entity.setThreshold(aup.getThreshold());
            }
            if (isNull(entity.getIsActive())) {
                entity.setIsActive(aup.getIsActive());
            }
            if (isNull(entity.getUserId())) {
                entity.setUserId(aup.getUserId());
            }
            if (isNull(entity.getAccount())) {
                entity.setAccountId(aup.getAccountId());
            }
            entity.setId(aup.getId());
            entity.setCreatedAt(aup.getCreatedAt());
            entity.setUpdatedAt(LocalDate.now());
        });
    }

    /**
     * Additional process after update
     *
     * @param id
     * @param entity
     * @throws BumsoftException
     */
    @Override
    public void processAfterUpdate(Long id, AutomatedPlan entity) throws BumsoftException {
        log.info("Process after AUP update");
        if (!id.equals(entity.getId())) {
            log.error("AUP update failed");
            throw new BumsoftException("Automated plan update failed");
        }
    }

    public boolean isAccountSubjectToAutomaticPlan(Long accountId) {
        return this.repository.findByAccountIdAndIsActiveIsTrue(accountId).isPresent();
    }
}
