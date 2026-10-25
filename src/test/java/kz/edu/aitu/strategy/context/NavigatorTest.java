package kz.edu.aitu.strategy.context;

import kz.edu.aitu.strategy.model.RoutePlan;
import kz.edu.aitu.strategy.model.RouteRequest;
import kz.edu.aitu.strategy.strategy.CarRouteStrategy;
import kz.edu.aitu.strategy.strategy.WalkingRouteStrategy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NavigatorTest {

    private final RouteRequest trip = new RouteRequest("AITU", "Central Park", 6.0);

    @Test
    void delegatesRoutePlanningToCurrentStrategy() {
        Navigator navigator = new Navigator(new CarRouteStrategy());

        RoutePlan route = navigator.buildRoute(trip);

        assertEquals("Car", route.transport());
        assertEquals(13, route.estimatedMinutes());
    }

    @Test
    void switchesStrategyAtRuntime() {
        Navigator navigator = new Navigator(new CarRouteStrategy());

        navigator.setRouteStrategy(new WalkingRouteStrategy());
        RoutePlan route = navigator.buildRoute(trip);

        assertEquals("Walking", route.transport());
        assertEquals(72, route.estimatedMinutes());
    }

    @Test
    void rejectsNullStrategy() {
        assertThrows(NullPointerException.class, () -> new Navigator(null));
    }
}

