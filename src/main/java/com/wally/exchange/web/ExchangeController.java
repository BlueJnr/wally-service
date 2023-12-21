package com.wally.exchange.web;

import com.wally.exchange.model.api.ExchangeResponse;
import com.wally.exchange.service.ExchangeService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/exchanges")
@RequiredArgsConstructor
public class ExchangeController implements ExchangeApi {
  private final ExchangeService exchangeService;

  @Override
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ExchangeResponse getExchange(
      @RequestParam BigDecimal amount,
      @RequestParam String sourceCurrency,
      @RequestParam String targetCurrency) {
    return exchangeService.getExchange(amount, sourceCurrency, targetCurrency);
  }
}
