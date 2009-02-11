package com.chuidiang.ejemplos.hibernate.ejemplo3;

import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.Session;

/**
 * Ejemplo sencillo de Hibernate con una asociación entre una entidad y una
 * tabla de valores primitivos.<br>
 * La clase Person puede tener asociados varios String e-mail. Se inserta una
 * Person en base de datos, luego se le asocia un e-mail y luego se hace una
 * consulta para mostrar los resultados
 * 
 * @author Chuidiang
 * 
 */
public class Ejemplo3 {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.WARN);
        new Ejemplo3();
        HibernateUtil.getSessionFactory().close();
    }

    public Ejemplo3() {
        // Se crea y guarda en bd una persona
        Long idPerson = createAndStorePerson("Juan", "Cortés", 34);

        // Se le asocia el e-mail.
        addEmailToPerson(idPerson, "direccion@correo.com");

        // Se listan las personas con sus eventos
        listPersons();
    }

    /**
     * Se anade el email a la persona cuyo id se le pasa.
     * 
     * @param personId
     * @param emailAddress
     */
    private void addEmailToPerson(Long personId, String emailAddress) {

        // Comienza la sesión
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Se carga la persona de bd
        Person aPerson = (Person) session.load(Person.class, personId);

        // Se le asocia el e-mail
        aPerson.getEmailAddresses().add(emailAddress);

        // Y se salva en base de datos.
        session.getTransaction().commit();
    }

    /**
     * Creacion y salvado de una persona en base de datos.
     * 
     * @param nombre
     * @param apellido
     * @param edad
     * @return
     */
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

    /**
     * Se listan las personas en base de datos.
     * 
     * @return
     */
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
}
