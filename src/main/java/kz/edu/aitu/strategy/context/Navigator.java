package kz.edu.aitu.strategy.context;

import kz.edu.aitu.strategy.model.RoutePlan;
import kz.edu.aitu.strategy.model.RouteRequest;
import kz.edu.aitu.strategy.strategy.RouteStrategy;

import java.util.Objects;

public class Navigator {

    private RouteStrategy routeStrategy;

    public Navigator(RouteStrategy routeStrategy) {
        setRouteStrategy(routeStrategy);
    }

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = Objects.requireNonNull(
                routeStrategy,
                "Route strategy must not be null"
        );
    }

    public RoutePlan buildRoute(RouteRequest request) {
        return routeStrategy.plan(request);
    }
}

