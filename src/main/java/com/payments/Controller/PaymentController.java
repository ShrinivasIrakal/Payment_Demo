package com.payments.Controller;

import com.payments.Entity.Payment;
import com.payments.Payload.PaymentDto;
import com.payments.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/immediatePayment/{accId}")
    public ResponseEntity<PaymentDto> makeImmediatePayment(
            @RequestBody PaymentDto paymentDto,
            @PathVariable Long accId
    ){
        PaymentDto payment1 = paymentService.immediatePayment(paymentDto, accId);
        return new ResponseEntity<>(payment1, HttpStatus.OK);
    }
}
