package com.ozan.exchange.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "currency_calculations")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyCalculationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String transactionId;

    @Column
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column private String sourceCurrency;

    @Column private String targetCurrency;

    @Column private BigDecimal amount;

    @Column(precision = 11, scale = 8)
    private BigDecimal rate;
}
