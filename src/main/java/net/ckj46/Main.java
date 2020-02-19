package net.ckj46;

import net.ckj46.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main (String[] args){
        // utworzenie entity manager'a
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // utworzenie obiektu encji
        Employee employee = new Employee();
        employee.setFirstName("Jarosław");
        employee.setLastName("Dupa-Kaczyński");
        employee.setSalary(1L);

        // transakcja
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
