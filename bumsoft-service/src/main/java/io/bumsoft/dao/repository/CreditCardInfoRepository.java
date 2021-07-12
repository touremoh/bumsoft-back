package io.bumsoft.dao.repository;

import io.bumsoft.dao.entity.CreditCardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardInfoRepository extends JpaRepository<CreditCardInfo, Long> {

}
