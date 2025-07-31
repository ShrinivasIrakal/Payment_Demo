package com.payments.Repository;

import com.payments.Entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {


    Optional<Beneficiary> findByRecipientAccountNumber(String recipientAccountNumber);

}