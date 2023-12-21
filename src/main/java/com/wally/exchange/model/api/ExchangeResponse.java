package com.wally.exchange.model.api;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ExchangeResponse", description = "Response model for currency exchange operations.")
public class ExchangeResponse {
  @Schema(
      description = "The original amount specified by the user in the source currency.",
      example = "20.00")
  private BigDecimal amount;

  @Schema(
      description =
          "Converted amount in the target currency, calculated based on the exchange rate.",
      example = "5.40")
  private BigDecimal convertedAmount;

  @Schema(description = "Currency code of the source.", example = "PEN")
  private String sourceCurrency;

  @Schema(description = "Currency code of the target.", example = "USD")
  private String targetCurrency;

  @Schema(
      description =
          "Exchange rate applied to convert the amount from the source currency to the target currency.",
      example = "0.27")
  private BigDecimal rate;
}
