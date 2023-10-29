package com.chuidiang.ejemplos.hibernate.ejemplo1;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * Ejemplo sencillo con Hibernate.<br>
 * Se crea un Evento y se inserta en base de datos. Luego se hace la consulta y
 * se presenta por pantalla.
 * 
 * @author Chuidiang
 * 
 */
public class Ejemplo1 {
    private final static Logger log = LoggerFactory.getLogger(Ejemplo1.class);

    public static void main(String[] args) {
        new Ejemplo1();
    }

    public Ejemplo1() {
        // Se inserta un evento en bd
        createAndStoreEvent("El Event", new Date());

        // Se hace la consulta y se lista
        listEvents();
        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreEvent(String title, Date theDate) {
        // Obtener session de hibernate y comenzar transaccion
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Creacion del evento
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);

        // Insercion en base de datos.
        session.save(theEvent);
        session.getTransaction().commit();
        log.info("Insertado: " + theEvent);
        return theEvent.getId();
    }

    private List<Event> listEvents() {
        // Obtener sesion de hibernate y comenzar transaccion
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Consulta y mostrar resultados.
        List<Event> result = (List<Event>) session.createQuery("from Event")
                .list();
        for (Event evento : result) {
            log.info("Leido: " + evento);
        }

        // Cierre de sesion.
        session.getTransaction().commit();

        return result;
    }

}
