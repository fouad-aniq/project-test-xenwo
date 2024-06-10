package com.example.payment.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Properties;
import java.security.SecurityException;

@Component
public class PaymentConfigManager implements com.example.payment.domain.ports.PaymentConfigService {

    private String apiKey;
    private String gatewayUrl;
    private final Environment env;

    @Autowired
    public PaymentConfigManager(Environment env) {
        this.env = env;
    }

    public void loadConfiguration(String configSource) throws IOException, SecurityException {
        Properties props = new Properties();
        try (var inputStream = this.getClass().getResourceAsStream(configSource)) {
            if (inputStream == null) {
                throw new IOException("Configuration file '" + configPath + "' not found in classpath.");
            }
            props.load(inputStream);
            this.apiKey = props.getProperty("payment.gateway.apiKey");
            this.gatewayUrl = props.getProperty("payment.gateway.url");
            validateConfiguration();
        } catch (IOException | SecurityException e) {
            throw new SecurityException("Error loading configuration from " + configSource, e);
        }
    }

    private void validateConfiguration() {
        if (apiKey == null || apiKey.trim().isEmpty() || gatewayUrl == null || gatewayUrl.trim().isEmpty()) {
            throw new IllegalStateException("API Key and Gateway URL must be set correctly.");
        }
    }

    public String getApiKey() {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("API key is not configured properly.");
        }
        return apiKey;
    }

    public String getGatewayUrl() {
        if (gatewayUrl == null || gatewayUrl.trim().isEmpty()) {
            throw new IllegalStateException("Gateway URL is not configured properly.");
        }
        return gatewayUrl;
    }
}