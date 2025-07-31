package com.payments.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senderAccountId")
    private Account senderAccount;

    private String recipientAccountNumber;
    private float amount;
    private String remarks;
    private String paymentMode;
    private boolean isScheduled;
    private String frequency;
    private int recurrenceCount;
    private LocalDate scheduledDate;
    private String status;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "beneficiary_id")
    private Beneficiary beneficiary;

    @OneToMany(mappedBy = "payment")
    private List<PaymentLog> logs;


}

