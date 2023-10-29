package com.chuidiang.ejemplos.hibernate_annotations;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Clase de utilidad para obtener la sesion de hibernate.
 * 
 * @author documentacion hibernate
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            // Si no ponemos fichero, intenta cargar "/hibernate.cfg.xml" en el
            // raiz
            sessionFactory = new Configuration().configure(
                    new File("hibernate.cfg.xml")).buildSessionFactory();
        } catch (Throwable ex) {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
}
