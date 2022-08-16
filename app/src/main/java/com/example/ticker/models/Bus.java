package com.example.ticker.models;

import java.util.List;

public class Bus {
    Integer bus_id;
    String bus_number;

    Integer route_id;
    String route_name;
    Integer starting_station_id;

    String starting_station_name;
    Integer ending_station_id;
    String ending_station_name;

    List<Stop>  stops;

    public Bus(Integer bus_id, String bus_number, Integer route_id, String route_name, Integer starting_station_id, String starting_station_name, Integer ending_station_id, String ending_station_name, List<Stop> stops) {
        this.bus_id = bus_id;
        this.bus_number = bus_number;
        this.route_id = route_id;
        this.route_name = route_name;
        this.starting_station_id = starting_station_id;
        this.starting_station_name = starting_station_name;
        this.ending_station_id = ending_station_id;
        this.ending_station_name = ending_station_name;
        this.stops = stops;
    }

    public Bus() {
    }

    public Integer getBus_id() {
        return bus_id;
    }

    public void setBus_id(Integer bus_id) {
        this.bus_id = bus_id;
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public Integer getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public Integer getStarting_station_id() {
        return starting_station_id;
    }

    public void setStarting_station_id(Integer starting_station_id) {
        this.starting_station_id = starting_station_id;
    }

    public String getStarting_station_name() {
        return starting_station_name;
    }

    public void setStarting_station_name(String starting_station_name) {
        this.starting_station_name = starting_station_name;
    }

    public Integer getEnding_station_id() {
        return ending_station_id;
    }

    public void setEnding_station_id(Integer ending_station_id) {
        this.ending_station_id = ending_station_id;
    }

    public String getEnding_station_name() {
        return ending_station_name;
    }

    public void setEnding_station_name(String ending_station_name) {
        this.ending_station_name = ending_station_name;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }
}
