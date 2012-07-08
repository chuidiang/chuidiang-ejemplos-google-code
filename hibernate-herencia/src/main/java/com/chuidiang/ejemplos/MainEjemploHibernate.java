package com.chuidiang.ejemplos;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

public class MainEjemploHibernate {
    public static void main(String[] args) {
        insertaAlgunDato();
        consultaDatos();
    }

    private static void consultaDatos() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Dato> datos = s.createQuery("from Dato").list();
        for (Dato dato : datos) {
            System.out.println(dato.getId());
            System.out.println(dato.getTitle());
            System.out.println(dato.getDate());
            System.out.println(dato.diQuienEres());
        }
        s.close();
    }

    private static void insertaAlgunDato() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Hijo1 d1 = new Hijo1();
            d1.setDate(new Date());
            d1.setTitle("titulo");
            d1.setNumero(22);
            
            Hijo2 d2 = new Hijo2();
            d2.setDate(new Date());
            d2.setTitle("otro titulo");
            d2.setBooleano(true);
            
            s.beginTransaction();
            s.save(d1);
            s.save(d2);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
    }
}
