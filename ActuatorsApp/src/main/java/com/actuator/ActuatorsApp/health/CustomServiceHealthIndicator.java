package com.actuator.ActuatorsApp.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomServiceHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        // Custom logic to check service health
        boolean serviceIsUp = checkServiceHealth();
        if (serviceIsUp) {
            return Health.up().withDetail("Custom Service", "Available").build();
        } else {
            return Health.down().withDetail("Custom Service", "Not Available").build();
        }
    }

    private boolean checkServiceHealth() {
        // Perform health check
        return true; // Example logic
    }
}
