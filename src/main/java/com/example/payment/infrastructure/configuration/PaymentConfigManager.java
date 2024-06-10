package com.example.payment.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.payment.domain.ports.PaymentConfigService;
import org.springframework.context.annotation.Profile;

@Component
public class PaymentConfigManager implements PaymentConfigService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentConfigManager.class);
    private String apiKey;
    private String gatewayUrl;
    private final Environment env;

    @Autowired
    public PaymentConfigManager(Environment env) {
        this.env = env;
    }

    @Profile({"dev", "test", "prod"})
    public void loadConfiguration(String configSource) throws IOException, SecurityException {
        try {
            Properties props = loadProperties(configSource);
            validateProperties(props);
        } catch (IOException e) {
            logger.error("IO Error loading configuration from " + configSource, e);
            throw e;
        } catch (SecurityException e) {
            logger.error("Security issue with loading configuration from " + configSource, e);
            throw e;
        }
    }

    private Properties loadProperties(String configSource) throws IOException {
        Properties props = new Properties();
        try (var inputStream = this.getClass().getResourceAsStream(configSource)) {
            if (inputStream == null) {
                throw new IOException("Configuration file '" + configSource + "' not found in classpath.");
            }
            props.load(inputStream);
            return props;
        }
    }

    private void validateProperties(Properties props) {
        apiKey = props.getProperty("payment.gateway.apiKey");
        gatewayUrl = props.getProperty("payment.gateway.url");
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
