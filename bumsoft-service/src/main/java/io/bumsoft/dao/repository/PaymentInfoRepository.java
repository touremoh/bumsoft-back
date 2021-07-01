package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Long> {

}
