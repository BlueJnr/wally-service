package com.wally.exchange.config.exception;

public class ExchangeNotFoundException extends RuntimeException {

  public ExchangeNotFoundException(String sourceCurrency, String targetCurrency) {
    super(
        "Exchange rate with sourceCurrency: "
            + sourceCurrency
            + ", targetCurrency: "
            + targetCurrency
            + " not found");
  }
}
