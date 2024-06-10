package com.example.payment.infrastructure.repositories;

import com.example.payment.domain.entities.PaymentTransactionEntity;
import com.example.payment.domain.ports.out.PaymentRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class PaymentRepositoryImpl implements PaymentRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(PaymentRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public PaymentTransactionEntity save(PaymentTransactionEntity transaction) {
        validatePaymentTransaction(transaction);
        entityManager.persist(transaction);
        logger.info("Saved transaction with ID: " + transaction.getTransactionId());
        return transaction;
    }

    private void validatePaymentTransaction(PaymentTransactionEntity transaction) {
        // Validation logic here
    }

    @Override
    @Transactional(readyOnly = true)
    public Optional<PaymentTransactionEntity> findById(Long id) {
        logger.info("Fetching transaction by ID: " + id);
        return Optional.ofNullable(entityManager.find(PaymentTransactionEntity.class, id));
    }
}