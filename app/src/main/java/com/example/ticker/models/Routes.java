package com.example.ticker.models;

import java.util.List;

public class Routes {

    List<Route> routes;

    public Routes(List<Route> routes) {
        this.routes = routes;
    }

    public Routes() {
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
