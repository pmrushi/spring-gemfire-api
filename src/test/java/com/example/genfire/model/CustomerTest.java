package com.example.genfire.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerTest {

    private Customer customer = new Customer(new Date(), "test", "id");

    @Test
    void getDate() {
        Date date = new Date();
        customer.setDate(date);
        assertEquals(date, customer.getDate());
    }

    @Test
    void getMessage() {
        String mess = "test";
        customer.setMessage(mess);
        assertEquals(mess, customer.getMessage());
    }

    @Test
    void getId() {
        String id = "id";
        customer.setId(id);
        assertEquals(id, customer.getId());
    }
}