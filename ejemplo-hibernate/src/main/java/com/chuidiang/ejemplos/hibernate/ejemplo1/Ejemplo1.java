package com.chuidiang.ejemplos.hibernate.ejemplo1;

import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.chuidiang.ejemplos.hibernate.ejemplo1.Event;

public class Ejemplo1 {
    private final static Logger log = Logger.getLogger(Ejemplo1.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getLogger("org.hibernate").setLevel(Level.WARN);
        new Ejemplo1();
    }

    public Ejemplo1() {
        createAndStoreEvent("El Event", new Date());
        listEvents();
        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);
        session.getTransaction().commit();
        log.info("Insertado: "+theEvent);
        return theEvent.getId();
    }

    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Event> result = (List<Event>)session.createQuery("from Event").list();
        session.getTransaction().commit();
        for (Event evento : result) {
            log.info("Leido: "+evento);
        }
        return result;
    }

}
