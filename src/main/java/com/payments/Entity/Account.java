package com.payments.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Setter
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @OneToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    private BigDecimal balance;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "account")
    private List<Beneficiary> beneficiaries;

    @OneToMany(mappedBy = "senderAccount")
    private List<Payment> payments;

    @OneToMany(mappedBy = "account")
    private List<CreditInterest> creditInterests;


}

