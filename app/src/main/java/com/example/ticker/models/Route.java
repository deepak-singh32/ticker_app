package com.example.ticker.models;

public class Route {
    String name,startStop,endStop;

    public Route(String name, String startStop, String endStop) {
        this.name = name;
        this.startStop = startStop;
        this.endStop = endStop;
    }

    public Route() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartStop() {
        return startStop;
    }

    public void setStartStop(String startStop) {
        this.startStop = startStop;
    }

    public String getEndStop() {
        return endStop;
    }

    public void setEndStop(String endStop) {
        this.endStop = endStop;
    }
}
