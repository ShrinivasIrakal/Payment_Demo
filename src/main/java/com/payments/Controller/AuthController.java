package com.payments.Controller;

import com.payments.Entity.Customer;
import com.payments.Payload.LoginRequest;
import com.payments.Payload.LoginResponse;
import com.payments.Repository.CustomerRepository;
import com.payments.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Customer customer = customerRepo.findById(request.getCustomerId()).orElseThrow(()->new RuntimeException("customer with Id not found"));
        if (customer != null && request.getPassword().equals(customer.getPasswordHash())) {
            String token = jwtService.generateToken(customer);
            return ResponseEntity.ok(new LoginResponse(token, "Login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "Invalid credentials"));
        }
    }
}

