package com.chuidiang.ejemplos.hibernate.ejemplo4;

import java.util.Date;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Ejemplo sencillo de Hibernate con una asociación entre dos tablas, con una
 * asociacion bidireccional<br>
 * La clase Person puede tener asociados varios Event y la clase Event puede
 * tener asociadas varias Person. Se inserta una Person en base de datos, luego
 * un Event, se asocian y luego se hace una consulta para mostrar los
 * resultados, tanto en un sentido como en otro
 * 
 * @author Chuidiang
 * 
 */
public class Ejemplo4 {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.WARN);
        new Ejemplo4();
        HibernateUtil.getSessionFactory().close();
    }

    public Ejemplo4() {
        // Se crea y guarda en bd un evento
        Long idEvent = createAndStoreEvent("El Event", new Date());

        // Se crea y guarda en bd una persona
        Long idPerson = createAndStorePerson("Juan", "Cortés", 34);

        // Se asocian en bd persona y evento
        addPersonToEvent(idPerson, idEvent);

        // Se listan las personas con sus eventos
        listPersons();

        // Se listan los eventos con sus participantes
        listEvents();
    }

    private void listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List<Event> eventos = (List<Event>) session.createQuery("from Event")
                .list();

        for (Event evento : eventos) {
            System.out.println(evento.toString());
        }
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
        // Se obtiene la sesion de hibernate
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Creación de la persona.
        Person p = new Person();
        p.setAge(edad);
        p.setFirstname(nombre);
        p.setLastname(apellido);

        // Se guardaen bd y se cierra la sesion
        session.save(p);
        session.getTransaction().commit();
        return p.getId();
    }

    private List<Person> listPersons() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Person> result = session.createQuery("from Person").list();

        // Debe hacerse el listado antes del commit, puesto que el toString()
        // de Person consulta los Event asociados a la persona.
        for (Person persona : result) {
            System.out.println(persona);
        }

        // Cierre de sesion
        session.getTransaction().commit();
        return result;
    }

    private void addPersonToEvent(Long personId, Long eventId) {
        // Sesion
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Se cargan perons y eventos
        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);

        // Se añade el evento a la persona
        aPerson.addToEvent(anEvent);

        // Se termina la transaccion
        session.getTransaction().commit();
    }
}
