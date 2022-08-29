package ru.chernyshev.model.homework;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepository();

        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        productRepository.add(entityManager, new Product("Bread", 20.0));
//        productRepository.add(entityManager, new Product("Milk", 50.0));
//        productRepository.add(entityManager, new Product("Chocolate", 70.0));

//        System.out.println(productRepository.findById(entityManager, 2L));
        System.out.println(productRepository.findAll(entityManager));
//        productRepository.deleteById(entityManager, 1L);
//        productRepository.update(entityManager, 2L, new Product("1234", 1900.0));

        entityManager.close();
        entityManagerFactory.close();
    }


}
