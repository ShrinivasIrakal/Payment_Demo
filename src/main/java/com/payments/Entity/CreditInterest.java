package com.payments.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
public class CreditInterest {
    @Id
    @GeneratedValue
    private UUID interestId;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    private LocalDate date;
    private BigDecimal closingBalance;
    private BigDecimal interestAmount;
}
