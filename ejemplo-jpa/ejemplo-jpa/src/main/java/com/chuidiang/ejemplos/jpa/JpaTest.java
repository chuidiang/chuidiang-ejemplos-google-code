package com.chuidiang.ejemplos.jpa;

import java.sql.DriverManager;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.chuidiang.ejemplos.domain.Department;
import com.chuidiang.ejemplos.domain.Employee;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
	   withHibernate();
		withEclipseLynK();
		DriverManager.getConnection(
		      "jdbc:derby:;shutdown=true");
	}
   private static void withHibernate() {
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("hibernatePersistenceUnit");
      EntityManager manager = factory.createEntityManager();
      JpaTest test = new JpaTest(manager);

      EntityTransaction tx = manager.getTransaction();
      tx.begin();
      try {
         test.createEmployees();
      } catch (Exception e) {
         e.printStackTrace();
      }
      tx.commit();

      test.listEmployees();

      
      System.out.println(".. done");

      
   }
   private static void withEclipseLynK() {
      EntityManagerFactory factory = Persistence.createEntityManagerFactory("eclipseLinkPersistenceUnit");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listEmployees();
		
		System.out.println(".. done");
   }




   private void createEmployees() {
		int numOfEmployees = manager.createQuery("Select a From Employee a", Employee.class).getResultList().size();
		if (numOfEmployees == 0) {
			Department department = new Department("java");
			manager.persist(department);

			manager.persist(new Employee("Jakab Gipsz",department));
			manager.persist(new Employee("Captain Nemo",department));

		}
	}


	private void listEmployees() {
		List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
		System.out.println("num of employess:" + resultList.size());
		for (Employee next : resultList) {
			System.out.println("next employee: " + next);
		}
	}


}
