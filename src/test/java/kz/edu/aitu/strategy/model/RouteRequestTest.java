package kz.edu.aitu.strategy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RouteRequestTest {

    @Test
    void storesValidTripData() {
        RouteRequest request = new RouteRequest("AITU", "Central Park", 6.0);

        assertEquals("AITU", request.from());
        assertEquals("Central Park", request.to());
        assertEquals(6.0, request.distanceKm());
    }

    @Test
    void rejectsBlankStartPoint() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RouteRequest(" ", "Central Park", 6.0)
        );
    }

    @Test
    void rejectsNonPositiveDistance() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new RouteRequest("AITU", "Central Park", 0)
        );
    }
}

