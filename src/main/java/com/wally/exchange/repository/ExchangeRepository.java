package com.wally.exchange.repository;

import com.wally.exchange.model.entity.ExchangeEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ExchangeRepository extends CrudRepository<ExchangeEntity, Integer> {
  Optional<ExchangeEntity> findBySourceCurrencyAndTargetCurrency(
      @Param("sourceCurrency") String sourceCurrency,
      @Param("targetCurrency") String targetCurrency);
}
