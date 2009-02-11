package com.chuidiang.ejemplos.hibernate.ejemplo4;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase Evento, persistente en base de datos en este ejemplo.
 * 
 * @author Chuidiang
 * 
 */
public class Event {
    /** Clave primaria */
    private Long id;

    private String title;
    private Date date;
    private Set participants = new HashSet();

    public Set getParticipants() {
        return participants;
    }

    public void setParticipants(Set participants) {
        this.participants = participants;
    }

    public Event() {
    }

    public Long getId() {
        return id;
    }

    /**
     * Metodo setter privado, hibernate es capaz de llamarlo, pero nosostros
     * desde codigo no.
     * 
     * @param id
     */
    private void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Evento : " + id + " - " + title + " - " + date);
        for (Person p : (Set<Person>) getParticipants()) {
            // No se usa p.toString() ya que este llama a Event.toString() y
            // sería recursivo sin fin.
            sb.append(System.getProperty("line.separator")
                    + "   Participante : " + p.getFirstname());
        }
        return sb.toString();
    }
}
