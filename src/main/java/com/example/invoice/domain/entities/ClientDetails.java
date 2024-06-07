package com.example.invoice.domain.entities;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class ClientDetails {

    private String name;
    private String address;
    private String contactInfo;
}