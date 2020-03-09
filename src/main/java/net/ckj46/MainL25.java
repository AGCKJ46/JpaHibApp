package net.ckj46;

import net.ckj46.domain.Address;
import net.ckj46.domain.Employee;
import net.ckj46.domain.Phone;
import net.ckj46.domain.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

public class MainL25 {
    public static void main (String[] args){
        System.out.println("------> utworzenie entity manager'a");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("------> persist");
        employeePersist(entityManager);

        System.out.println("------> zamykanie entity manager'a");
        entityManager.close();
        entityManagerFactory.close();
        System.out.println("------> end");
    }

    private static void employeePersist(EntityManager entityManager) {
        // utworzenie obiektu encji
        Address address = new Address("Wypierdkowo Wielkie", "99-666", "Ujowa", 666);
        Employee employee = new Employee("Jarosław", "Uj-Kaczyński", 9L, address);

        Phone phone1 = new Phone("H", "+48 61 66-69-99", employee);
        Phone phone2 = new Phone("W", "+48 61 22-66-88", employee);

        List<Employee> employees = new LinkedList<>();
        employees.add(employee);

        Project project1 = new Project("Upadek Polski", employees);
        Project project2 = new Project("PolExit", employees);

        // transakcja
        entityManager.getTransaction().begin();

        entityManager.persist(address);
        entityManager.persist(phone1);
        entityManager.persist(phone2);
        entityManager.persist(project1);
        entityManager.persist(project2);
        entityManager.persist(employee);

        entityManager.getTransaction().commit();

        entityManager.refresh(employee);
        System.out.println("Employee: " + employee.toString());
    }
}
