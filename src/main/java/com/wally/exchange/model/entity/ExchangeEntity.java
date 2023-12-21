package com.wally.exchange.model.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchange")
public class ExchangeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "exchange_id")
  private Integer id;

  @Column(name = "source_currency", nullable = false, columnDefinition = "CHAR(3)")
  private String sourceCurrency;

  @Column(name = "target_currency", nullable = false, columnDefinition = "CHAR(3)")
  private String targetCurrency;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal rate;
}
