package net.ckj46;

import net.ckj46.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainForFindAndModify {
    public static void main (String[] args){
        // utworzenie entity manager'a
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, 1L);
        System.out.println("Employee: " + employee.toString());
        employee.setSalary(0L);
        entityManager.getTransaction().commit();

        employee = entityManager.find(Employee.class, 1L);
        System.out.println("Employee: " + employee.toString());

        entityManager.close();
        entityManagerFactory.close();
    }
}
