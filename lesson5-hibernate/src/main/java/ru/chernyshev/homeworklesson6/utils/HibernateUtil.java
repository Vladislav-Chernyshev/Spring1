package ru.chernyshev.homeworklesson6.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import org.hibernate.cfg.Configuration;

@Getter
public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    private static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void shutdown(){
        getEntityManager().close();
    }


}
