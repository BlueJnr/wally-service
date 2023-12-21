package com.wally.exchange.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wally.exchange.model.api.ExchangeResponse;
import com.wally.exchange.repository.ExchangeRepository;
import com.wally.exchange.service.impl.ExchangeServiceImpl;
import com.wally.exchange.util.DataUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {
  @Mock private ExchangeRepository exchangeRepository;
  @InjectMocks private ExchangeServiceImpl exchangeService;

  @Test
  @DisplayName(
      """
                      Given an exchange rate
                          For source currency PEN
                          And target currency USD

                      When the getExchange() method is executed
                          With amount 20.000
                          With source currency PEN
                          And target currency USD

                      Then Should return exchange
                          With converted amount
                          With the original amount 20.00
                          With source currency PEN
                          With target currency USD
                          And the rate""")
  void getExchange() {
    BigDecimal amount = BigDecimal.valueOf(20.00);
    String sourceCurrency = "PEN";
    String targetCurrency = "USD";

    when(exchangeRepository.findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency))
        .thenReturn(Optional.of(DataUtils.EXCHANGE_ENTITY_OBJECT_1));

    ExchangeResponse exchangeResponse =
        exchangeService.getExchange(amount, sourceCurrency, targetCurrency);

    assertEquals(amount, exchangeResponse.getAmount());
    assertEquals(BigDecimal.valueOf(5.40).setScale(2, RoundingMode.HALF_UP), exchangeResponse.getConvertedAmount());
    assertEquals(sourceCurrency, exchangeResponse.getSourceCurrency());
    assertEquals(targetCurrency, exchangeResponse.getTargetCurrency());
    assertEquals(BigDecimal.valueOf(0.27), exchangeResponse.getRate());

    verify(exchangeRepository)
        .findBySourceCurrencyAndTargetCurrency(sourceCurrency, targetCurrency);
  }
}
