package com.example.invoice.domain.entities;

import java.time.LocalDateTime;

public class ChangeLogEntry {
    private LocalDateTime timestamp;
    private String userId;
    private String actionType;
    private String description;

    public ChangeLogEntry(LocalDateTime timestamp, String userId, String actionType, String description) {
        this.timestamp = timestamp;
        this.userId = userId;
        this.actionType = actionType;
        this.description = description;
    }
}