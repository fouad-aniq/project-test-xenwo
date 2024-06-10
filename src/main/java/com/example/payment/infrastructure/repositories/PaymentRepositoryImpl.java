package com.example.payment.infrastructure.repositories;

import com.example.payment.domain.entities.PaymentTransactionEntity;
import com.example.payment.domain.ports.out.PaymentRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 * Concrete implementation of the PaymentRepository interface using JPA.
 */
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves a given payment transaction entity into the database and returns the persisted entity.
     *
     * @param transaction The payment transaction entity to save.
     * @return The persisted payment transaction entity.
     */
    @Override
    @Transactional
    public PaymentTransactionEntity save(PaymentTransactionEntity transaction) {
        entityManager.persist(transaction);
        return transaction;
    }

    /**
     * Retrieves a payment transaction by its ID if it exists in the database.
     *
     * @param id The transaction ID.
     * @return An Optional containing the payment transaction entity if found, otherwise empty.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentTransactionEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(PaymentTransactionEntity.class, id));
    }
}