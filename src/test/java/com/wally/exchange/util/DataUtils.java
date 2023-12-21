package com.wally.exchange.util;

import com.wally.exchange.model.api.ExchangeResponse;
import com.wally.exchange.model.entity.ExchangeEntity;
import java.math.BigDecimal;

public class DataUtils {

  public static final ExchangeResponse EXCHANGE_RESPONSE_OBJECT_1 =
      new ExchangeResponse(
          BigDecimal.valueOf(20.00),
          BigDecimal.valueOf(5.4),
          "PEN",
          "USD",
          BigDecimal.valueOf(0.27));

  public static final ExchangeEntity EXCHANGE_ENTITY_OBJECT_1 =
      new ExchangeEntity(1, "PEN", "USD", BigDecimal.valueOf(0.27));
}
