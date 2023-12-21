package com.wally.exchange.web;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.wally.exchange.service.ExchangeService;
import com.wally.exchange.util.DataUtils;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(MockitoExtension.class)
class ExchangeControllerTest {
  @Mock private ExchangeService exchangeService;
  @InjectMocks private ExchangeController exchangeController;
  private MockMvc mockMvc;

  @BeforeEach
  public void setUp() {
    mockMvc = standaloneSetup(exchangeController).build();
  }

  @Test
  @DisplayName(
      """
                  Given an exchange rate
                      For source currency PEN
                      And target currency USD

                  When I request the exchange rate
                      For amount 20.000
                      For source currency PEN
                      And target currency USD

                  Then I should get converted amount
                      With the original amount 20.00
                      With source currency PEN
                      With target currency USD
                      And the rate""")
  void getExchange() throws Exception {
    BigDecimal amount = BigDecimal.valueOf(20.00);
    String sourceCurrency = "PEN";
    String targetCurrency = "USD";

    when(exchangeService.getExchange(any(BigDecimal.class), anyString(), anyString()))
        .thenReturn(DataUtils.EXCHANGE_RESPONSE_OBJECT_1);

    ResultActions result =
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exchanges")
                .contentType(MediaType.APPLICATION_JSON)
                .param("amount", amount.toString())
                .param("sourceCurrency", sourceCurrency)
                .param("targetCurrency", targetCurrency));

    result
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(
            content()
                .json(
                    """
                                {
                                    "amount": 20.00,
                                    "convertedAmount": 5.4,
                                    "sourceCurrency": "PEN",
                                    "targetCurrency": "USD",
                                    "rate": 0.27
                                }"""));

    verify(exchangeService).getExchange(any(BigDecimal.class), anyString(), anyString());
  }
}
