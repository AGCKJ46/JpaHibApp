package net.ckj46;

import net.ckj46.domain.Address;
import net.ckj46.domain.Employee;

import javax.persistence.*;
import java.util.Iterator;
import java.util.List;

public class MainForJPQL {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main (String[] args){
        // utworzenie entity manager'a
        entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        entityManager = entityManagerFactory.createEntityManager();

        addEmployees();

        TypedQuery<Employee> typedQuery = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName='Gnom'", Employee.class);
        Employee employee = typedQuery.getSingleResult();
        System.out.println("Emploee: "+employee.toString());

        typedQuery = entityManager.createQuery("SELECT e FROM Employee e WHERE e.lastName='Nowak'", Employee.class);
        List<Employee> employeeList = typedQuery.getResultList();
        for (Employee empl: employeeList) {
            System.out.println("Emploee from list: " + empl.toString());
        }

        Query query = entityManager.createQuery("SELECT concat(e.firstName, '', e.lastName), e.salary *0.19 FROM Employee e");
        Iterator iterator = query.getResultList().iterator();
        while(iterator.hasNext()){
            Object[] item = (Object[]) iterator.next();
            System.out.println("Imię i nazwisko: "+item[0]+" musi zapłacić "+item[1]);
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
