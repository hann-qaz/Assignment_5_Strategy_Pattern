package kz.edu.aitu.strategy.strategy;

import kz.edu.aitu.strategy.model.RoutePlan;
import kz.edu.aitu.strategy.model.RouteRequest;

public class BicycleRouteStrategy implements RouteStrategy {

    private static final double AVERAGE_SPEED_KM_H = 15.0;
    private static final int PARKING_MINUTES = 2;

    @Override
    public RoutePlan plan(RouteRequest request) {
        int minutes = (int) Math.ceil(request.distanceKm() / AVERAGE_SPEED_KM_H * 60)
                + PARKING_MINUTES;

        return new RoutePlan(
                "Bicycle",
                request.from(),
                request.to(),
                request.distanceKm(),
                minutes,
                "Prefer bicycle lanes; bicycle parking time is included."
        );
    }
}

