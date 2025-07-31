package com.payments.Payload;

import com.payments.Entity.Account;
import com.payments.Entity.Beneficiary;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long id;


    private Long senderAccountNo;

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

    private Long beneficiaryId;
}
