package net.ckj46;

import net.ckj46.domain.Address;
import net.ckj46.domain.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainForJPQLParams {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main (String[] args){
        // utworzenie entity manager'a
        entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        entityManager = entityManagerFactory.createEntityManager();

        addEmployees();

        System.out.println("---- ---- ---- ---- ---- ----");
        TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary>:minSalary", Employee.class);
        query.setParameter("minSalary", 3000L); // pierwszy sposób parametryzacji (parametry nazwane)
        List<Employee> resultList = query.getResultList();

        for (Employee e: resultList) {
            System.out.println("Pracownicy: " + e.toString());
        }

        System.out.println("---- ---- ---- ---- ---- ----");
        query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.salary > ?1 AND e.salary < ?2", Employee.class);
        query.setParameter(1, 3000L); // drógi sposób parametryzacji (przez index)
        query.setParameter(2, 3500L);
        resultList = query.getResultList();

        for (Employee e: resultList) {
            System.out.println("Pracownicy: " + e.toString());
        }

        System.out.println("---- ---- ---- ---- ---- ----");
        query = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName IN :names", Employee.class);

        List<String> names = new ArrayList<>();
        names.add("Nowak");
        names.add("Kwal");

        query.setParameter("names", names); // pierwszy sposób parametryzacji (parametry nazwane)
        resultList = query.getResultList();

        for (Employee e: resultList) {
            System.out.println("Pracownicy: " + e.toString());
        }



        entityManager.close();
        entityManagerFactory.close();
    }

    private static void addEmployees() {
        addEmployee(
                new Employee("Jan", "Nowak", 3000L,
                        new Address("Poznań", "60-666", "Diabelska", 666)));
        addEmployee(
                new Employee("Marek", "Kwal", 3100L,
                        new Address("Poznań", "60-666", "Szatańska", 999)));

        addEmployee(
                new Employee("Jarek", "Gnom", 3100L,
                        new Address("Warszawa", "02-222", "Żliborska", 0)));

        addEmployee(
                new Employee("Maria", "Nowak", 3500L,
                        new Address("Poznań", "60-666", "Diabelska", 666)));
    }

    private static void addEmployee(Employee employee) {
        // transakcja
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.persist(employee.getAddress());
        entityManager.getTransaction().commit();
    }
}
