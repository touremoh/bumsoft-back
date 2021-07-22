package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.Subscription;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends BumsoftRepository<Subscription, Long> {
}
