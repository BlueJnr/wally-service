package com.wally.exchange.web;

import com.wally.exchange.config.GlobalExceptionHandler;
import com.wally.exchange.model.api.ExchangeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;

@Tag(name = "exchanges", description = "Operations related to exchange rates")
public interface ExchangeApi {
  @Operation(
      tags = {"exchanges"},
      summary = "Get exchange rate",
      description =
          "Retrieve the exchange rate and convert an amount from the source currency to the target currency.",
      operationId = "getExchange",
      responses = {
        @ApiResponse(
            responseCode = "200",
            description =
                "Success - The exchange rate and converted amount are successfully returned.",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ExchangeResponse.class))),
        @ApiResponse(
            responseCode = "404",
            description =
                "Exchange rate not found - The specified exchange rate could not be found.",
            content =
                @Content(
                    mediaType = "application/json",
                    examples =
                        @ExampleObject(
                            name = "exchangeRateNotFound",
                            description =
                                "Error response when the requested exchange rate is not available in the system.",
                            value =
                                """
                                            {
                                               "title": "Exchange rate not found",
                                               "source": "DATA_REPOSITORY",
                                               "detail": "Exchange rate with sourceCurrency: PEN, targetCurrency: USD not found"
                                            }"""),
                    schema = @Schema(implementation = GlobalExceptionHandler.ErrorResponse.class)))
      })
  ExchangeResponse getExchange(
      @Parameter(
              description = "The amount in the source currency that needs to be converted.",
              example = "20.00",
              required = true)
          BigDecimal amount,
      @Parameter(
              description = "The currency code of the source currency.",
              example = "PEN",
              required = true)
          String sourceCurrency,
      @Parameter(
              description = "The currency code of the target currency.",
              example = "USD",
              required = true)
          String targetCurrency);
}
