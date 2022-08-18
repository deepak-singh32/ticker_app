package com.example.ticker.models;

public class BusTicket {


    String bus_number;
    String route;
    String booking_time;
    String starting_station;
    String ending_station;
    Integer num_ticket;
    Integer fare;


//     "bus_number": "DL1PC5308",
//             "route": "479STL",
//             "booking_time": "2022-08-18T06:41:56.527Z",
//             "starting_station": "Kushak Nallah Depot",
//             "ending_station": "Badarpur Border (T)",
//             "num_ticket": 1,
//             "fare": 15



    public BusTicket(String bus_number, String route, String booking_time, String starting_station, String ending_station, Integer num_ticket, Integer fare) {
        this.bus_number = bus_number;
        this.route = route;
        this.booking_time = booking_time;
        this.starting_station = starting_station;
        this.ending_station = ending_station;
        this.num_ticket = num_ticket;
        this.fare = fare;
    }

    public BusTicket() {
    }

    public String getBus_number() {
        return bus_number;
    }

    public void setBus_number(String bus_number) {
        this.bus_number = bus_number;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public String getStarting_station() {
        return starting_station;
    }

    public void setStarting_station(String starting_station) {
        this.starting_station = starting_station;
    }

    public String getEnding_station() {
        return ending_station;
    }

    public void setEnding_station(String ending_station) {
        this.ending_station = ending_station;
    }

    public Integer getNum_ticket() {
        return num_ticket;
    }

    public void setNum_ticket(Integer num_ticket) {
        this.num_ticket = num_ticket;
    }

    public Integer getFare() {
        return fare;
    }

    public void setFare(Integer fare) {
        this.fare = fare;
    }
}
