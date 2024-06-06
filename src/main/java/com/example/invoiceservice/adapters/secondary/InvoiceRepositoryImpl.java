package com.example.invoiceservice.adapters.secondary;

import com.example.invoiceservice.domain.entities.InvoiceEntity;
import com.example.invoiceservice.domain.exceptions.DatabaseAccessException;
import com.example.invoiceservice.domain.ports.InvoiceRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

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
            logger.error("Failed to save the invoice.", e);
            throw new DatabaseAccessException("Failed to save the invoice.", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceEntity> findById(Long id) throws DatabaseAccessException {
        try {
            InvoiceEntity invoice = entityManager.find(InvoiceEntity.class, id);
            if (invoice == null) {
                logger.debug("No invoice found with ID: {}", id);
                return Optional.empty();
            } else {
                logger.debug("Found invoice: {}", invoice);
                return Optional.of(invoice);
            }
        } catch (Exception e) {
            logger.error("Failed to find the invoice by ID.\