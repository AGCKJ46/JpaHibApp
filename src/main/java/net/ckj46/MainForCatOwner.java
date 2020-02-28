package net.ckj46;

import net.ckj46.domain.Cat;
import net.ckj46.domain.CatOwner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MainForCatOwner {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main (String[] args){
        // utworzenie entity manager'a
        entityManagerFactory = Persistence.createEntityManagerFactory("mysql");
        entityManager = entityManagerFactory.createEntityManager();

        CatOwner owner = new CatOwner();
        owner.setFirstName("Jarosław");
        owner.setLastName("Dupa-Żoliborski");

        Cat cat = new Cat();
        cat.setName("Killer");

        owner.setCat(cat);

        entityManager.getTransaction().begin();
        entityManager.persist(owner);
        entityManager.persist(cat);
        entityManager.getTransaction().commit();

        entityManager.refresh(cat);

        entityManager.close();
        entityManagerFactory.close();
    }
}
