package com.example.ticker.models;

import com.google.gson.annotations.SerializedName;

public class Route {

    Integer route_id;

    @SerializedName("route_name")
    String name;


    Integer starting_station_id;

    @SerializedName("starting_station_name")
    String startStop;


    Integer ending_station_id;

    @SerializedName("ending_station_name")
    String endStop;


    public Route(Integer route_id, String name, Integer starting_station_id, String startStop, Integer ending_station_id, String endStop) {
        this.route_id = route_id;
        this.name = name;
        this.starting_station_id = starting_station_id;
        this.startStop = startStop;
        this.ending_station_id = ending_station_id;
        this.endStop = endStop;
    }

    public Integer getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Integer route_id) {
        this.route_id = route_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStarting_station_id() {
        return starting_station_id;
    }

    public void setStarting_station_id(Integer starting_station_id) {
        this.starting_station_id = starting_station_id;
    }

    public String getStartStop() {
        return startStop;
    }

    public void setStartStop(String startStop) {
        this.startStop = startStop;
    }

    public Integer getEnding_station_id() {
        return ending_station_id;
    }

    public void setEnding_station_id(Integer ending_station_id) {
        this.ending_station_id = ending_station_id;
    }

    public String getEndStop() {
        return endStop;
    }

    public void setEndStop(String endStop) {
        this.endStop = endStop;
    }

    public Route() {
    }


}
