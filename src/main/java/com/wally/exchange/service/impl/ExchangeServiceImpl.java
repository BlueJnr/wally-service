package com.wally.exchange.service.impl;

import com.wally.exchange.config.exception.ExchangeNotFoundException;
import com.wally.exchange.model.api.ExchangeResponse;
import com.wally.exchange.model.entity.ExchangeEntity;
import com.wally.exchange.repository.ExchangeRepository;
import com.wally.exchange.service.ExchangeService;
import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {
  private final ExchangeRepository exchangeRepository;

  @Override
  public ExchangeResponse getExchange(
      BigDecimal amount, String sourceCurrency, String targetCurrency) {
    return exchangeRepository
        .findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency)
        .map(exchange -> buildResponse(exchange, amount))
        .orElseThrow(() -> new ExchangeNotFoundException(sourceCurrency, targetCurrency));
  }

  private ExchangeResponse buildResponse(ExchangeEntity exchange, BigDecimal amount) {
    ExchangeResponse exchangeResponse = new ExchangeResponse();
    exchangeResponse.setAmount(amount);
    exchangeResponse.setConvertedAmount(amount.multiply(exchange.getRate()).setScale(2, RoundingMode.HALF_UP));
    exchangeResponse.setSourceCurrency(exchange.getSourceCurrency());
    exchangeResponse.setTargetCurrency(exchange.getTargetCurrency());
    exchangeResponse.setRate(exchange.getRate());
    return exchangeResponse;
  }
}
