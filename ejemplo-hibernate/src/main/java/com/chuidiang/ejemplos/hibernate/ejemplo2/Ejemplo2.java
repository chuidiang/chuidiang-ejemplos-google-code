package com.chuidiang.ejemplos.hibernate.ejemplo2;

import com.chuidiang.ejemplos.hibernate.ejemplo1.Event;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

/**
 * Ejemplo sencillo de Hibernate con una asociaci�n entre dos tablas.<br>
 * La clase Person puede tener asociados varios Event. Se inserta una Person en
 * base de datos, luego un Event, se asocian y luego se hace una consulta para
 * mostrar los resultados
 * 
 * @author Chuidiang
 * 
 */
public class Ejemplo2 {

    public static void main(String[] args) {
        new Ejemplo2();
        HibernateUtil.getSessionFactory().close();
    }

    public Ejemplo2() {
        // Se crea y guarda en bd un evento
        Long idEvent = createAndStoreEvent("El Event", new Date());

        // Se crea y guarda en bd una persona
        Long idPerson = createAndStorePerson("Juan", "Cortés", 34);

        // Se asocian en bd persona y evento
        addPersonToEvent(idPerson, idEvent);

        // Se a�ade un nuevo evento a la persona
        addNewEventToPerson("Nuevo evento", new Date(), idPerson);

        // Se listan las personas con sus eventos
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
        // Se obtiene la sesion de hibernate
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Creaci�n de la persona.
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

        // Se a�ade el evento a la persona
        aPerson.getEvents().add(anEvent);

        // Se termina la transaccion
        session.getTransaction().commit();
    }

    private void addNewEventToPerson(String eventName, Date eventDate,
            Long personId) {
        // Sesion
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Se carga la persona de bd
        Person aPerson = (Person) session.load(Person.class, personId);

        // Se crea un evento y se salva
        Event e = new Event();
        e.setTitle(eventName);
        e.setDate(eventDate);
        session.save(e);

        // Se a�ade el evento a la persona y se salva
        aPerson.getEvents().add(e);
        session.save(aPerson);

        // fin de sesion
        session.getTransaction().commit();
    }
}
