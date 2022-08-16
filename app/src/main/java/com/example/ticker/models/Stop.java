package com.example.ticker.models;

public class Stop {
    String station_id;
    String station_name;
    String stop_number;

    public Stop(String station_id, String station_name, String stop_number) {
        this.station_id = station_id;
        this.station_name = station_name;
        this.stop_number = stop_number;
    }

    public Stop() {
    }

    public String getStation_id() {
        return station_id;
    }

    public void setStation_id(String station_id) {
        this.station_id = station_id;
    }

    public String getStation_name() {
        return station_name;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public String getStop_number() {
        return stop_number;
    }

    public void setStop_number(String stop_number) {
        this.stop_number = stop_number;
    }
}
