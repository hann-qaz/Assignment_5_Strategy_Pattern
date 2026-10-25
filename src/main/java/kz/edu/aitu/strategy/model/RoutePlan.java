package kz.edu.aitu.strategy.model;

public record RoutePlan(
        String transport,
        String from,
        String to,
        double distanceKm,
        int estimatedMinutes,
        String details
) {

    @Override
    public String toString() {
        return "%s: %s -> %s, %.1f km, about %d min. %s"
                .formatted(transport, from, to, distanceKm, estimatedMinutes, details);
    }
}

