package kz.edu.aitu.strategy.client;

import kz.edu.aitu.strategy.context.Navigator;
import kz.edu.aitu.strategy.model.RouteRequest;
import kz.edu.aitu.strategy.strategy.BicycleRouteStrategy;
import kz.edu.aitu.strategy.strategy.CarRouteStrategy;
import kz.edu.aitu.strategy.strategy.PublicTransportRouteStrategy;
import kz.edu.aitu.strategy.strategy.WalkingRouteStrategy;

public class NavigationDemo {

    public static void main(String[] args) {
        RouteRequest trip = new RouteRequest("AITU", "Central Park", 6.0);

        Navigator navigator = new Navigator(new CarRouteStrategy());
        System.out.println(navigator.buildRoute(trip));

        navigator.setRouteStrategy(new WalkingRouteStrategy());
        System.out.println(navigator.buildRoute(trip));

        navigator.setRouteStrategy(new BicycleRouteStrategy());
        System.out.println(navigator.buildRoute(trip));

        navigator.setRouteStrategy(new PublicTransportRouteStrategy());
        System.out.println(navigator.buildRoute(trip));
    }
}

