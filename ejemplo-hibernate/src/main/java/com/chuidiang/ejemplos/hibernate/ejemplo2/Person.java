package com.chuidiang.ejemplos.hibernate.ejemplo2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.chuidiang.ejemplos.hibernate.ejemplo1.Event;

public class Person {
    private Long id;
    private int age;
    private String firstname;
    private String lastname;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    private Set events = new HashSet();

    public Set getEvents() {
        return events;
    }

    public void setEvents(Set events) {
        this.events = events;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        StringBuilder sb = new StringBuilder();
        sb.append("Persona: " + id + " - " + firstname + " - " + lastname
                + " - " + age);
        Iterator<Event> eventos = getEvents().iterator();
        while (eventos.hasNext())
            sb.append(System.getProperty("line.separator") + "   Evento : "
                    + eventos.next());
        return sb.toString();
    }
}
