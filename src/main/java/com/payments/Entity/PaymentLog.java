package com.payments.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class PaymentLog {
    @Id
    @GeneratedValue
    private UUID logId;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    private Payment payment;
    private String eventType;
    private String message;
    private LocalDateTime timestamp;
}

