package com.platovco.vtb.models;

import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.PolylineMapObject;
import com.yandex.mapkit.transport.masstransit.Route;

import java.util.ArrayList;

public class ActualRoute {
    public enum RouteType {
        DRIVING,
        PEDESTRIAN
    }
    private RouteType routeType;
    private PolylineMapObject route;
    private Point destinationPoint;

    public ActualRoute(RouteType routeType, PolylineMapObject route, Point destinationPoint) {
        this.routeType = routeType;
        this.route = route;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }
    public PolylineMapObject getRoute() {
        return route;
    }

    public void setRoute(PolylineMapObject route) {
        this.route = route;
    }

    public Point getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(Point destinationPoint) {
        this.destinationPoint = destinationPoint;
    }
}
