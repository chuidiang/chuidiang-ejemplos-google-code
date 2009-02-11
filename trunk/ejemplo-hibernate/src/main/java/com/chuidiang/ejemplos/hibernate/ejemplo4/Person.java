package com.chuidiang.ejemplos.hibernate.ejemplo4;

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

    public Person() {
    }

    public Long getId() {
        return id;
    }

    private Set events = new HashSet();

    /**
     * Se hace este metodo protegido, para que nadie pueda cambiar el conjunto
     * de eventos sin control
     * 
     * @return
     */
    protected Set getEvents() {
        return events;
    }

    /**
     * Se hace este metodo protegido, para que nadie pueda cambiar el conjunto
     * de eventos sin control
     * 
     * @param events
     */
    protected void setEvents(Set events) {
        this.events = events;
    }

    /**
     * Al anadir un evento y ser la asociacion bidireccional, hay que asociar el
     * evento a esta persona y luego asociar esta persona al evento.
     * 
     * @param event
     */
    public void addToEvent(Event event) {
        this.getEvents().add(event);
        event.getParticipants().add(this);
    }

    /**
     * Al borrar un evento y ser la asocacion bidireccional, hay que eliminar el
     * evento de esta persona y luego eliminar esta persona del evento.
     * 
     * @param event
     */
    public void removeFromEvent(Event event) {
        this.getEvents().remove(event);
        event.getParticipants().remove(this);
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona: " + id + " - " + firstname + " - " + lastname
                + " - " + age);
        Iterator<Event> eventos = getEvents().iterator();
        while (eventos.hasNext())
            sb.append(System.getProperty("line.separator") + "   Evento : "
                    + eventos.next().toString());
        return sb.toString();
    }
}
