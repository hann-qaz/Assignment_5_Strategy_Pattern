# Assignment 5 Strategy Pattern Report

**Student:** Amirkhan Tulkibayev  
**Group:** SE-2522  
**Domain:** Navigation and route planning

## 1. Introduction

This project applies the Strategy pattern to route planning. The algorithm family contains four interchangeable ways to plan the same trip: car, walking, bicycle, and public transport. Every algorithm receives the same `RouteRequest` and returns a `RoutePlan`, but each one calculates time with its own speed and extra delay. The client can replace the algorithm while the program is running.

## 2. UML class diagram

![Strategy pattern class diagram](uml-class-diagram.svg)

- `RouteStrategy` is the common Strategy interface.
- `CarRouteStrategy`, `WalkingRouteStrategy`, `BicycleRouteStrategy`, and `PublicTransportRouteStrategy` are Concrete Strategies.
- `Navigator` is the Context. It stores a `RouteStrategy` reference and delegates route planning.
- `NavigationDemo` is the Client. It creates strategies and switches them at runtime.

## 3. Clean Code

### 3.1 Polymorphism replaces strategy-selection conditionals

**Before:** one method would need a new branch for every transport type.

```java
switch (transportType) {
    case "car" -> calculateCarRoute(request);
    case "walk" -> calculateWalkingRoute(request);
    case "bicycle" -> calculateBicycleRoute(request);
}
```

**After:** `Navigator` delegates through the common interface.

```java
public RoutePlan buildRoute(RouteRequest request) {
    return routeStrategy.plan(request);
}
```

This removes conditional logic for choosing an algorithm from the Context. The validation `if` statements in `RouteRequest` only reject invalid input; they do not select a strategy.

### 3.2 Meaningful names reveal intent

**Before:** names such as `TypeA`, `doIt()`, and `x` do not explain the domain.

```java
class TypeA { Result doIt(Data x) { ... } }
```

**After:** names describe the role and data clearly.

```java
class WalkingRouteStrategy implements RouteStrategy {
    public RoutePlan plan(RouteRequest request) { ... }
}
```

`WalkingRouteStrategy`, `RouteRequest`, `estimatedMinutes`, and `buildRoute` make the code readable without extra comments.

### 3.3 Each class has one responsibility

**Before:** one large `Navigator` would calculate car, walking, bicycle, and bus routes.

```java
class Navigator {
    // car formula + walking formula + bicycle formula + bus formula
}
```

**After:** each Concrete Strategy contains one route algorithm.

```java
public class BicycleRouteStrategy implements RouteStrategy {
    public RoutePlan plan(RouteRequest request) {
        // only the bicycle calculation
    }
}
```

Changing bicycle speed cannot accidentally change the walking or car algorithm.

### 3.4 The Context is algorithm-agnostic

**Before:** a Context coupled to concrete classes would create them itself.

```java
private final CarRouteStrategy strategy = new CarRouteStrategy();
```

**After:** the Context depends only on the interface.

```java
private RouteStrategy routeStrategy;

public void setRouteStrategy(RouteStrategy routeStrategy) {
    this.routeStrategy = Objects.requireNonNull(routeStrategy);
}
```

`Navigator` does not know the formulas or concrete strategy types. It only knows the `RouteStrategy` contract.

### 3.5 The design is open for extension

**Before:** adding public transport to a `switch` would require editing existing selection code.

```java
case "bus" -> calculateBusRoute(request);
```

**After:** the new behavior is added as a separate class.

```java
public class PublicTransportRouteStrategy implements RouteStrategy {
    public RoutePlan plan(RouteRequest request) { ... }
}
```

No change was required in `Navigator`, `RouteStrategy`, or the other strategies. The Client only passes a `PublicTransportRouteStrategy` object to the Context.

## 4. Strategy vs. State

The course comparison asks a useful question: Strategy answers **which algorithm should perform the task**, while State answers **which lifecycle stage is the object currently in**. In Strategy, the caller or configuration normally chooses the behavior. In State, the Context or a state object usually controls transitions and changes how events are handled. This navigation problem needs Strategy because car, walking, bicycle, and public transport are alternative algorithms for one trip. They are not ordered stages, there are no transition rules, and the caller may choose any route method at any time. Runtime switching alone does not make the design State.

## 5. Conclusion

The Strategy pattern adds several small classes, but it makes the design flexible and easy to test. `Navigator` stays simple, each algorithm can change independently, and another route type can be added without modifying existing classes. The extra classes are a reasonable trade-off for clear responsibilities and safe extension.

## 6. GitHub repository

<https://github.com/hann-qaz/Assignment_5_Strategy_Pattern>

## References

1. Ospanov, Almas. *Lecture 7 The Strategy Pattern*. Astana IT University, 2026.
2. Bekenov, Yerassyl. *Strategy vs. State Untangling Two Look-Alike Design Patterns*. Astana IT University, 20 October 2026.

