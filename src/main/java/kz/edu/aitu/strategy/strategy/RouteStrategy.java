package kz.edu.aitu.strategy.strategy;

import kz.edu.aitu.strategy.model.RoutePlan;
import kz.edu.aitu.strategy.model.RouteRequest;

public interface RouteStrategy {

    RoutePlan plan(RouteRequest request);
}

