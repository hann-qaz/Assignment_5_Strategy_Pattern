package kz.edu.aitu.strategy.strategy;

import kz.edu.aitu.strategy.model.RoutePlan;
import kz.edu.aitu.strategy.model.RouteRequest;

public class WalkingRouteStrategy implements RouteStrategy {

    private static final double AVERAGE_SPEED_KM_H = 5.0;

    @Override
    public RoutePlan plan(RouteRequest request) {
        int minutes = (int) Math.ceil(request.distanceKm() / AVERAGE_SPEED_KM_H * 60);

        return new RoutePlan(
                "Walking",
                request.from(),
                request.to(),
                request.distanceKm(),
                minutes,
                "Use sidewalks and pedestrian crossings."
        );
    }
}

