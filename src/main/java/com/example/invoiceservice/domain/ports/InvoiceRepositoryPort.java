package com.example.invoiceservice.domain.ports;

import com.example.invoiceservice.domain.entities.InvoiceEntity;
import com.example.invoiceservice.domain.exceptions.DatabaseAccessException;
import java.util.Optional;

/**
 * Defines the port through which the domain layer can communicate with the database to perform operations like saving and retrieving invoices.
 */
public interface InvoiceRepositoryPort {

    /**
     * Saves an InvoiceEntity to the database and returns the saved entity.
     * Can throw DatabaseAccessException in case of database errors.
     *
     * @param invoice the invoice entity to save
     * @return the saved invoice entity
     * @throws DatabaseAccessException if there is a database error
     */
    InvoiceEntity save(InvoiceEntity invoice) throws DatabaseAccessException;

    /**
     * Retrieves an InvoiceEntity by its ID from the database if present.
     * Can throw DatabaseAccessException if the retrieval operation fails.
     *
     * @param id the ID of the invoice
     * @return an Optional containing the retrieved invoice or empty if not found
     * @throws DatabaseAccessException if there is a retrieval error
     */
    Optional<InvoiceEntity> findById(Long id) throws DatabaseAccessException;

    /**
     * Starts a new database transaction to ensure data consistency across operations.
     * This should be called before any set of database operations that need to be atomic.
     */
    void beginTransaction();
}