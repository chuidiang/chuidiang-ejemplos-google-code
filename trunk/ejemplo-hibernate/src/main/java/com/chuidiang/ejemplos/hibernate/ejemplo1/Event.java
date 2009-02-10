package com.chuidiang.ejemplos.hibernate.ejemplo1;

import java.util.Date;

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
        return "Evento : " + id + " - " + title + " - " + date;
    }
}
