package com.wally.exchange.config;

import com.wally.exchange.config.exception.ExchangeNotFoundException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ExchangeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ErrorResponse handleExchangeNotFoundException(ExchangeNotFoundException e) {
    return new ErrorResponse("Exchange rate not found", "DATA_REPOSITORY", e.getMessage());
  }

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @Schema(name = "ErrorResponse", description = "Error Response")
  public static class ErrorResponse {
    @NotNull
    @Schema(description = "Error response title", example = "Exchange rate not found")
    private String title;

    @NotNull
    @Schema(description = "Error response source", example = "DATA_REPOSITORY")
    private String source;

    @NotNull
    @Schema(
        description = "Error response detail",
        example = "Exchange rate with sourceCurrency: PEN, targetCurrency: USD not found")
    private String detail;
  }
}
