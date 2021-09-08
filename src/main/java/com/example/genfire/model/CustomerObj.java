package com.example.genfire.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.util.concurrent.atomic.AtomicLong;

@Region("customer")
public class CustomerObj {

    private static AtomicLong counter = new AtomicLong(0L);

    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private int age;

    @PersistenceConstructor
    public CustomerObj() {
        this.id = counter.incrementAndGet();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String toString() {
        return "firstname: " + firstname + " ,lastname: " + lastname + " ,age: " + age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}