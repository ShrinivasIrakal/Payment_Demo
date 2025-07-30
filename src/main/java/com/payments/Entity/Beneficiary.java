package com.payments.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    private String recipientAccountNumber;
    private String bankName;


}

