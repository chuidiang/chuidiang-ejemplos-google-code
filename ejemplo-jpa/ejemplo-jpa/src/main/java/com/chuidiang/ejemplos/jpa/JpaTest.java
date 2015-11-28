package com.chuidiang.ejemplos.jpa;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.chuidiang.ejemplos.domain.Employee;

public class JpaTest {
   
   static {
      
      // Elimina el log de hibernate
      System.setProperty("java.util.logging.config.file","");
   }

   private EntityManager manager;
   
   private final static Logger LOG = Logger.getLogger(JpaTest.class.getName());

   public JpaTest(EntityManager manager) {
      this.manager = manager;
   }

   /**
    * @param args
    * @throws Exception
    */
   public static void main(String[] args) throws Exception {
      System.out.println("HIBERNATE :\n");
      doJPAMagic("hibernatePersistenceUnit");
      
      System.out.println("ECLIPSELINK :");
      doJPAMagic("eclipseLinkPersistenceUnit");
   }


   private static void doJPAMagic(String persistenceUnit) {
      EntityManagerFactory factory = Persistence
            .createEntityManagerFactory(persistenceUnit);
      EntityManager manager = factory.createEntityManager();
      
      JpaTest test = new JpaTest(manager);

      test.createEmployees();
      test.listEmployees();
      System.out.println();
      
      Employee employee = test.queryForEmployee("Jakab Gipsz");
      System.out.println();
      
      test.modifyEmployee(employee.getId(), "Jakab Lopez");
      test.listEmployees();
      System.out.println();
      
      test.deleteEmployee(employee.getId());
      test.listEmployees();
      System.out.println();

      System.out.println(".. done");

      manager.close();
      factory.close();
   }

   private void modifyEmployee(Long id, String newName) {
      System.out.println("MODIFIY EMPLOYEE "+id);
      EntityTransaction tx = manager.getTransaction();
      tx.begin();

      try {
         Employee anEmployee = manager.find(Employee.class, id);
         anEmployee.setName(newName);
         manager.persist(anEmployee);
         tx.commit();
      } catch (Exception e) {
         e.printStackTrace();
         tx.rollback();
      }

   }

   private void deleteEmployee(Long id) {
      System.out.println("DELETE EMPLOYEE "+id);
      EntityTransaction tx = manager.getTransaction();
      tx.begin();
      
      try {
         Employee anEmployee = manager.find(Employee.class, id);
         manager.remove(anEmployee);
         tx.commit();
      } catch (Exception e){
         e.printStackTrace();
         tx.rollback();
      }
      
   }

   private Employee queryForEmployee(String name) {
      System.out.println("QUERY FOR "+name);
      Query query = manager
            .createQuery("Select a from Employee a where a.name = :name");
      query.setParameter("name", name);
      Employee anEmployee = (Employee) query.getSingleResult();
      System.out.println("Result : " + anEmployee.toString());
      return anEmployee;
   }

   private void createEmployees() {
      System.out.println("CREATE EMPLOYEES :");
      EntityTransaction tx = manager.getTransaction();
      tx.begin();

      try {
         manager.persist(new Employee("Jakab Gipsz"));
         manager.persist(new Employee("Captain Nemo"));
         tx.commit();
      } catch (Exception e) {
         e.printStackTrace();
         tx.rollback();
      }
   }

   private void listEmployees() {
      System.out.println("Employees list :");
      List<Employee> resultList = manager.createQuery(
            "Select a From Employee a", Employee.class).getResultList();
      System.out.println("num of employess:" + resultList.size());
      for (Employee next : resultList) {
         System.out.println("next employee: " + next);
      }
   }

}
