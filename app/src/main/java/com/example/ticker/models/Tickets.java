package com.example.ticker.models;

import java.util.List;

public class Tickets {
    List<BusTicket> tickets;

    public Tickets(List<BusTicket> tickets) {
        this.tickets = tickets;
    }

    public Tickets() {
    }

    public List<BusTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<BusTicket> tickets) {
        this.tickets = tickets;
    }
}
