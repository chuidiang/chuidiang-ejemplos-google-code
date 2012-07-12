package com.chuidiang.ejemplos.tabla_por_subclase;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

public class MainEjemploTablaPorSubclase {
    public static void main(String[] args) {
        insertaAlgunDato();
        consultaDatos();
    }

    private static void consultaDatos() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List<Padre> datos = s.createQuery("from Padre").list();
        for (Padre dato : datos) {
            System.out.println(dato.getId());
            System.out.println(dato.getAtributoPadre());
            System.out.println(dato.getDate());
            System.out.println(dato.diQuienEres());
        }
        s.close();
    }

    private static void insertaAlgunDato() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            Hija1 d1 = new Hija1();
            d1.setDate(new Date());
            d1.setAtributoPadre("titulo");
            d1.setAtributoHija1(22);
            
            Hija2 d2 = new Hija2();
            d2.setDate(new Date());
            d2.setAtributoPadre("otro titulo");
            d2.setAtributoHija2(true);
            
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
