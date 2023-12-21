package com.wally.exchange.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "ExchangeRequest",
    description = "Request model for initiating a currency exchange operation.")
public class ExchangeRequest {

  @Schema(
      description = "The amount in the source currency that needs to be converted.",
      example = "20.00")
  private BigDecimal amount;

  @Schema(description = "The currency code of the source currency.", example = "PEN")
  private String sourceCurrency;

  @Schema(description = "The currency code of the target currency.", example = "USD")
  private String targetCurrency;
}
