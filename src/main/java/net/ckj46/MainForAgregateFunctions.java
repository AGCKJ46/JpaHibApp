package net.ckj46;

import net.ckj46.domain.Address;
import net.ckj46.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MainForAgregateFunctions {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main (String[] args){
        // utworzenie entity manager'a
        entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        entityManager = entityManagerFactory.createEntityManager();

        addEmployees();

        Query query = entityManager.createQuery("SELECT avg(e.salary), " +
                                                            "min(e.salary), " +
                                                            "max(e.salary), " +
                                                            "sum(e.salary), " +
                                                            "count(e.id) " +
                                                        "FROM Employee e");
        Object[] result = (Object[]) query.getSingleResult();

        System.out.println("Średnia pensja: " + result[0]);
        System.out.println("Minimalna pensja: " + result[1]);
        System.out.println("Maksymalna pensja: " + result[2]);
        System.out.println("Suma pensji: " + result[3]);
        System.out.println("Liczba pracowników: " + result[4]);

        query = entityManager.createQuery("SELECT " +
                                                    "substring(e.firstName, 1, 3), " +
                                                    "trim(e.lastName), " +
                                                    "lower(e.firstName), " +
                                                    "upper(e.lastName), " +
                                                    "length(e.firstName) " +
                                                "FROM Employee e WHERE e.id=1");
        result = (Object[]) query.getSingleResult();

        System.out.println("3 pierwsze litery: " + result[0]);
        System.out.println("Trim: " + result[1]);
        System.out.println("Lower: " + result[2]);
        System.out.println("Upper: " + result[3]);
        System.out.println("Length: " + result[4]);

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
