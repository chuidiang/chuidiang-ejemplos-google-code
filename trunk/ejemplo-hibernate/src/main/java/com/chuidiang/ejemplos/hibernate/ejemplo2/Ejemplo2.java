package com.chuidiang.ejemplos.hibernate.ejemplo2;

import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.chuidiang.ejemplos.hibernate.ejemplo1.Event;

public class Ejemplo2 {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.WARN);
        new Ejemplo2();
        HibernateUtil.getSessionFactory().close();
    }

    public Ejemplo2() {
        Long idEvent = createAndStoreEvent("El Event", new Date());
        Long idPerson = createAndStorePerson("Juan", "Cortés", 34);
        addPersonToEvent(idPerson, idEvent);

        listPersons();
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

    private Long createAndStorePerson(String nombre, String apellido, int edad) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person p = new Person();
        p.setAge(edad);
        p.setFirstname(nombre);
        p.setLastname(apellido);
        session.save(p);
        session.getTransaction().commit();
        return p.getId();
    }

    private List<Person> listPersons() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Person> result = session.createQuery("from Person").list();
        for (Person persona : result) {
            System.out.println(persona);
        }
        session.getTransaction().commit();
        return result;
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);
        session.getTransaction().commit();
    }
}
