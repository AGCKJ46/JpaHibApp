package net.ckj46;

import net.ckj46.domain.Address;
import net.ckj46.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainForRemove {
    public static void main (String[] args){
        // utworzenie entity manager'a
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        removePersist(entityManager);
        // possibleTypes(entityManager);
        // dateTimeExample(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void removePersist(EntityManager entityManager) {
        // utworzenie obiektu encji
        Employee employee = new Employee();
        employee.setFirstName("Jarosław");
        employee.setLastName("Uj-Kaczyński");
        employee.setSalary(10L);

        Address address = new Address();
        address.setLocality("Wypierdkowo Wielkie");
        address.setStreet("Ujowa");
        address.setStreetNumber(666);
        address.setZipCode("99-666");

        employee.setAddress(address);

        System.out.println(employee.toString());

        // transakcja
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.persist(address);
        entityManager.getTransaction().commit();

        // transakcja
        entityManager.getTransaction().begin();
        entityManager.remove(employee);
        // entityManager.remove(address);
        entityManager.getTransaction().commit();

        System.out.println(employee.toString());
    }
}
