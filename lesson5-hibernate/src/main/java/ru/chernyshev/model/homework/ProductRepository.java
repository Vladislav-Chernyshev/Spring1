package ru.chernyshev.model.homework;


import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductRepository {

    //add
    public void add(EntityManager entityManager, Product product) {

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

    }


    //findById
    public Product findById(EntityManager entityManager, Long id) {
        return entityManager.find(Product.class, id);
    }


    //findAll
    public List<Product> findAll(EntityManager entityManager) {
        return entityManager.createQuery("""
                FROM Product
                """, Product.class).getResultList();
    }


    //deleteById
    public void deleteById(EntityManager entityManager, Long id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, 1L);
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }


    //saveOrUpdate
    public void update(EntityManager entityManager, Long id, Product product) {
        entityManager.getTransaction().begin();
        Product updateProduct = new Product(product.getTitle(), product.getCost());
        updateProduct.setId(id);
        entityManager.merge(updateProduct);
        entityManager.getTransaction().commit();
    }


}
