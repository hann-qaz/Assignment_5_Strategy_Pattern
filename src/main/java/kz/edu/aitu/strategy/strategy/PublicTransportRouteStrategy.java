package kz.edu.aitu.strategy.strategy;

import kz.edu.aitu.strategy.model.RoutePlan;
import kz.edu.aitu.strategy.model.RouteRequest;

public class PublicTransportRouteStrategy implements RouteStrategy {

    private static final double AVERAGE_SPEED_KM_H = 25.0;
    private static final int WAITING_MINUTES = 8;

    @Override
    public RoutePlan plan(RouteRequest request) {
        int minutes = (int) Math.ceil(request.distanceKm() / AVERAGE_SPEED_KM_H * 60)
                + WAITING_MINUTES;

        return new RoutePlan(
                "Public transport",
                request.from(),
                request.to(),
                request.distanceKm(),
                minutes,
                "Bus waiting time is included."
        );
    }
}

