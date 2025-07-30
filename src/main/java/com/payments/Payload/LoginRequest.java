package com.payments.Payload;

import lombok.Data;

@Data
public class LoginRequest {
    private Long customerId;
    private String password;

    public LoginRequest(Long customerId, String password) {
        this.customerId = customerId;
        this.password = password;
    }
}

