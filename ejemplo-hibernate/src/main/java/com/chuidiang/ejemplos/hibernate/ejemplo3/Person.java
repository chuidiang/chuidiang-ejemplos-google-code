package com.chuidiang.ejemplos.hibernate.ejemplo3;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Clase Person, persistente en base de datos en este ejemplo y con varios Event
 * asociados.
 * 
 * @author Chuidiang
 * 
 */
public class Person {
    /** Clave primaria */
    private Long id;
    private int age;
    private String firstname;
    private String lastname;
    private Set emailAddresses = new HashSet();
    private Set events = new HashSet();

    public Set getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public Set getEvents() {
        return events;
    }

    public void setEvents(Set events) {
        this.events = events;
    }

    /** Metodo setter privado, hibernate puede llamarlo, pero nosotros no */
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

    /**
     * Escribe los datos de la persona y sus e-mail.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona: " + id + " - " + firstname + " - " + lastname
                + " - " + age);
        Iterator<String> direcciones = getEmailAddresses().iterator();
        while (direcciones.hasNext())
            sb.append(System.getProperty("line.separator") + "   e-mail : "
                    + direcciones.next());
        return sb.toString();
    }
}
