package com.example.invoiceservice.adapters.secondary;

import com.example.invoiceservice.domain.entities.InvoiceEntity;
import com.example.invoiceservice.domain.exceptions.DatabaseAccessException;
import com.example.invoiceservice.domain.ports.InvoiceRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepositoryPort {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public InvoiceEntity save(InvoiceEntity invoice) throws DatabaseAccessException {
        try {
            entityManager.persist(invoice);
            logger.debug("Saving invoice: {}", invoice);
            return invoice;
        } catch (Exception e) {
            logger.error("Failed to save the invoice: " + e.getMessage(), e);
            throw new DatabaseAccessException("Error saving the invoice due to an exception.", e);
        }
    }

    @Override
    public Optional<InvoiceEntity> findById(Long id) throws DatabaseAccessException {
        try {
            return Optional.ofNullable(entityManager.find(InvoiceEntity.class, id));
        } catch (Exception e) {
            logger.error("Failed to find invoice: " + e.getMessage(), e);
            throw new DatabaseAccessException("Error retrieving the invoice with ID: " + id, e);
        }
    }

    @Override
    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void rollbackTransaction() {
        entityManager.getTransaction().rollback();
    }

    @Override
    public void commitTransaction() {
        entityManager.getTransaction().commit();
    }
}