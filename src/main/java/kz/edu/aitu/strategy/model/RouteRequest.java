package kz.edu.aitu.strategy.model;

import java.util.Objects;

public record RouteRequest(String from, String to, double distanceKm) {

    public RouteRequest {
        Objects.requireNonNull(from, "Start point must not be null");
        Objects.requireNonNull(to, "Destination must not be null");

        if (from.isBlank() || to.isBlank()) {
            throw new IllegalArgumentException("Start point and destination must not be blank");
        }
        if (!Double.isFinite(distanceKm) || distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be a positive number");
        }
    }
}

