package net.ckj46;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main (String[] args){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("h2db");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.close();
        entityManagerFactory.close();
    }

}
