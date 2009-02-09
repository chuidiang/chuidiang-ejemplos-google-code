package com.chuidiang.ejemplos.hibernate.ejemplo2;

import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.chuidiang.ejemplos.hibernate.ejemplo1.Event;
import com.chuidiang.ejemplos.hibernate.ejemplo1.HibernateUtil;

public class Ejemplo2 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.WARN);
        new Ejemplo2();
//        Long idPerson = mgr.createAndStorePerson("Pedro", "Loco", 33);
//        mgr.addPersonToEvent(idPerson, idEvent);
//        mgr.modificaEventos();
        HibernateUtil.getSessionFactory().close();
    }

    public Ejemplo2() {
        Long idEvent = createAndStoreEvent("El Event", new Date());
        List<Event> lista = listEvents();
    }
    private Long createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        session.save(theEvent);
        session.getTransaction().commit();
        return theEvent.getId();
    }
    
//    private Long createAndStorePerson (String nombre, String apellido, int edad) {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        Person p = new Person();
//        p.setAge(edad);
//        p.setFirstname(nombre);
//        p.setLastname(apellido);
//        session.save(p);
//        session.getTransaction().commit();
//        return p.getId();
//    }

//    private List<Person> modificaEventos() {
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        List<Person> result = session.createQuery("from Person").list();
//        for (Person persona: result) {
//            Iterator iterador = persona.getEvents().iterator();
//            while (iterador.hasNext()) {
//                Event evento = (Event)iterador.next();
//                evento.setTitle("Evento de "+persona.getFirstname());
//            }
//        }
//        session.getTransaction().commit();
//        return result;
//    }
    
    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Event> result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        for (Event evento: result) {
            System.out.println(evento);
        }
        return result;
    }
    
//    private void addPersonToEvent(Long personId, Long eventId) {
//        Session session =
//        HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//        Person aPerson = (Person) session.load(Person.class, personId);
//        Event anEvent = (Event) session.load(Event.class, eventId);
//        aPerson.getEvents().add(anEvent);
//        session.getTransaction().commit();
//    }
}
