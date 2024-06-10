package com.example.payment.infrastructure.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.Getter;

public class PaymentConfigManager {

    @Getter private String apiKey;
    @Getter private String gatewayUrl;

    public void loadConfiguration(String configSource) throws IOException, SecurityException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(configSource)) {
            properties.load(input);
        }
        this.apiKey = properties.getProperty("api.key");
        this.gatewayUrl = properties.getProperty("gateway.url");
        if (apiKey == null || gatewayUrl == null) {
            throw new IllegalArgumentException("API key or Gateway URL is missing in the configuration.");
        }
    }
}
