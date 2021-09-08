package com.example.genfire.model;

import java.util.Date;

public class Customer {

    private Date date;
    private String message;
    private String id;

    public Customer() {
    }
    public Customer(Date date, String message, String id) {
        this.date = date;
        this.message = message;
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}