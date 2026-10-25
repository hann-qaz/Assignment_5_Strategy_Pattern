package kz.edu.aitu.strategy.strategy;

import kz.edu.aitu.strategy.model.RoutePlan;
import kz.edu.aitu.strategy.model.RouteRequest;

public class CarRouteStrategy implements RouteStrategy {

    private static final double AVERAGE_SPEED_KM_H = 50.0;
    private static final int PARKING_MINUTES = 5;

    @Override
    public RoutePlan plan(RouteRequest request) {
        int minutes = (int) Math.ceil(request.distanceKm() / AVERAGE_SPEED_KM_H * 60)
                + PARKING_MINUTES;

        return new RoutePlan(
                "Car",
                request.from(),
                request.to(),
                request.distanceKm(),
                minutes,
                "Use main roads; parking time is included."
        );
    }
}

