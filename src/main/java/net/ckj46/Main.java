package net.ckj46;

import net.ckj46.domain.Address;
import net.ckj46.domain.DateTimeExample;
import net.ckj46.domain.Employee;
import net.ckj46.domain.PossibleTypes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main (String[] args){
        // utworzenie entity manager'a
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        employeePersist(entityManager);
        // possibleTypes(entityManager);
        // dateTimeExample(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void employeePersist(EntityManager entityManager) {
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

        System.out.println(employee.toString());

        entityManager.refresh(employee);

        System.out.println(employee.toString());
    }

    private static void possibleTypes(EntityManager entityManager) {
        // utworzenie obiektu encji
        PossibleTypes possibleTypes = new PossibleTypes();
        int[] ia = new int[4];
        ia[0]=1;
        ia[1]=2;
        ia[2]=4;
        ia[3]=8;
        possibleTypes.setIntArray(ia);

        // transakcja
        entityManager.getTransaction().begin();
        entityManager.persist(possibleTypes);
        entityManager.getTransaction().commit();
    }

    private static void dateTimeExample(EntityManager entityManager) {
        // utworzenie obiektu encji
        DateTimeExample dateTimeExample = new DateTimeExample();
        dateTimeExample.setLocalDateTime(LocalDateTime.now());

        // transakcja
        entityManager.getTransaction().begin();
        entityManager.persist(dateTimeExample);
        entityManager.getTransaction().commit();
    }
}
