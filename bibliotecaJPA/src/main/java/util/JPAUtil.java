package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory emf =  Persistence.createEntityManagerFactory("connectJPA");

    public static EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    public static void closeFactory(){
        emf.close();
    }
}
