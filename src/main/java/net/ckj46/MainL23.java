package net.ckj46;

import net.ckj46.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class MainL23 {
    public static void main (String[] args){
        System.out.println("------> utworzenie entity manager'a");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("------> persist");
        employeePersist(entityManager);
        // possibleTypes(entityManager);
        // dateTimeExample(entityManager);

        System.out.println("------> zamykanie entity manager'a");
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("------> end");
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

        Phone phone1 = new Phone("H", "+48 61 66-69-99");
        employee.addPhone(phone1);

        Phone phone2 = new Phone("W", "+48 61 22-66-88");
        employee.addPhone(phone2);

        // transakcja
        entityManager.getTransaction().begin();

        entityManager.persist(employee);
        entityManager.persist(address);
        entityManager.persist(phone1);
        entityManager.persist(phone2);

        Employee readEmployee = entityManager.find(Employee.class, 1L);
        System.out.println("Employee: " + readEmployee.toString());

        entityManager.getTransaction().commit();
    }
}
