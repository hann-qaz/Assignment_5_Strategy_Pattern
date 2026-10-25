# Defense Notes

## 30-second explanation

The project plans the same trip in different ways. `RouteStrategy` is the common algorithm interface. Car, walking, bicycle, and public transport classes implement different time calculations. `Navigator` stores only a `RouteStrategy` reference and delegates to it. The client can call `setRouteStrategy(...)` at runtime, so `Navigator` contains no `if` or `switch` for transport types.

## Live demo

```bash
mvn test
mvn exec:java
```

Point to these lines during the defense:

1. `RouteStrategy.plan(...)` is the common contract.
2. Each concrete class contains one route algorithm.
3. `Navigator.buildRoute(...)` delegates with one line.
4. `NavigationDemo` switches strategies on the same `Navigator` object.
5. `PublicTransportRouteStrategy` demonstrates extension without changing `Navigator`.

## Likely questions

**Why is this Strategy?** The client chooses one of several algorithms for the same task: planning a route.

**Why is this not State?** There is no lifecycle or required transition order. A route does not move through stages. The caller freely chooses the algorithm.

**Where was conditional logic removed?** A possible `switch (transportType)` was replaced by polymorphic calls through `RouteStrategy`.

**How do you add a new strategy?** Create a class that implements `RouteStrategy`. No existing strategy or `Navigator` code must change.

**What is the trade-off?** The design adds small classes, but each algorithm is easier to test, replace, and understand.

