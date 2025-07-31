package com.payments.Service;

import com.payments.Entity.Account;
import com.payments.Entity.Beneficiary;
import com.payments.Entity.Payment;
import com.payments.Payload.PaymentDto;
import com.payments.Repository.AccountRepository;
import com.payments.Repository.BeneficiaryRepository;
import com.payments.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    public PaymentDto immediatePayment(PaymentDto paymentDto,Long accountId){

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account with Id not found"));

        Long benfId = paymentDto.getBeneficiaryId();

        Beneficiary beneficiary = beneficiaryRepository.findById(benfId).orElseThrow(() -> new RuntimeException("Beneficiary Id not found"));

        String benfBankName = beneficiary.getBankName();
        if(benfBankName.equals("ASY Bank") || paymentDto.getAmount()<account.getBalance()){
            float newBalance = account.getBalance() - paymentDto.getAmount();
            account.setBalance(newBalance);
            accountRepository.save(account);

            Account beneficiaryAccount = beneficiary.getAccount();

            float totalBal = beneficiaryAccount.getBalance() + paymentDto.getAmount();
            beneficiaryAccount.setBalance(totalBal);

            accountRepository.save(beneficiaryAccount);




            paymentDto.setStatus("Successful payment");
            Payment payment = mapToEntity(paymentDto);

            Account senderAccount = accountRepository.findById(paymentDto.getSenderAccountNo()).orElseThrow(() -> new RuntimeException("Sender Account Not found"));

            payment.setSenderAccount(senderAccount);

//            Beneficiary beneficiary = beneficiaryRepository.findById(paymentDto.getBeneficiaryId()).orElseThrow(() -> new RuntimeException("Beneficiary Id not found"));
            payment.setBeneficiary(beneficiary);
            Payment saved = paymentRepository.save(payment);

            return mapToDto(saved);

        }
        throw new RuntimeException("Payment unsuccessful");
    }






    public PaymentDto mapToDto(Payment payment){
        PaymentDto dto =new PaymentDto();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMode(payment.getPaymentMode());
        dto.setCreatedAt(payment.getCreatedAt());
        dto.setFrequency(payment.getFrequency());
        dto.setStatus(payment.getStatus());
        dto.setRemarks(payment.getRemarks());
        dto.setRecipientAccountNumber(payment.getRecipientAccountNumber());
        dto.setRecurrenceCount(payment.getRecurrenceCount());
        dto.setScheduled(payment.isScheduled());
        dto.setSenderAccountNo(payment.getSenderAccount().getAccountId());
        dto.setBeneficiaryId(payment.getBeneficiary().getId());
        dto.setRecipientAccountNumber(payment.getRecipientAccountNumber());
        return dto;
    }


    public Payment mapToEntity(PaymentDto dto){
        Payment payment = new Payment();
        payment.setPaymentMode(dto.getPaymentMode());
        payment.setAmount(dto.getAmount());
        payment.setFrequency(dto.getFrequency());
        payment.setCreatedAt(dto.getCreatedAt());
        payment.setRemarks(dto.getRemarks());
        payment.setScheduled(dto.isScheduled());
        payment.setRecurrenceCount(dto.getRecurrenceCount());
        payment.setStatus(dto.getStatus());
        payment.setRecipientAccountNumber(dto.getRecipientAccountNumber());


        //Account and beneficiary we need to manually set after finding it using repos

        return payment;
    }
}
