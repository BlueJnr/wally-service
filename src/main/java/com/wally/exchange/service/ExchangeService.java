package com.wally.exchange.service;

import com.wally.exchange.model.api.ExchangeResponse;
import java.math.BigDecimal;

public interface ExchangeService {
  ExchangeResponse getExchange(BigDecimal amount, String sourceCurrency, String targetCurrency);
}
