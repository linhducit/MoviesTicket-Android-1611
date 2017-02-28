package com.pt.movieticket.model;

/**
 * Created by TitTit on 29/11/2016.
 */

public class Order {
    private String theater;
    private String room;
    private String movie;
    private String dateTime;
    private String ticket;
    private String total;

    public Order(String theater, String room, String movie, String dateTime, String ticket, String total) {
        this.setTheater(theater);
        this.setRoom(room);
        this.setMovie(movie);
        this.setDateTime(dateTime);
        this.setTicket(ticket);
        this.setTotal(total);
    }

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
