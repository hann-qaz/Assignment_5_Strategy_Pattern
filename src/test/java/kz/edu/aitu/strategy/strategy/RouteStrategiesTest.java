package kz.edu.aitu.strategy.strategy;

import kz.edu.aitu.strategy.model.RouteRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteStrategiesTest {

    private final RouteRequest trip = new RouteRequest("AITU", "Central Park", 6.0);

    @Test
    void carStrategyIncludesDrivingAndParkingTime() {
        assertEquals(13, new CarRouteStrategy().plan(trip).estimatedMinutes());
    }

    @Test
    void walkingStrategyUsesWalkingSpeed() {
        assertEquals(72, new WalkingRouteStrategy().plan(trip).estimatedMinutes());
    }

    @Test
    void bicycleStrategyIncludesBicycleParkingTime() {
        assertEquals(26, new BicycleRouteStrategy().plan(trip).estimatedMinutes());
    }

    @Test
    void publicTransportStrategyIncludesWaitingTime() {
        assertEquals(23, new PublicTransportRouteStrategy().plan(trip).estimatedMinutes());
    }
}

