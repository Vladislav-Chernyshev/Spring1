package ru.chernyshev.homeworklesson6;

import org.springframework.stereotype.Repository;
import ru.chernyshev.homeworklesson6.utils.HibernateUtil;

import java.util.List;

@Repository
public class ProductRepository {

    public List<Product> findAll() {
        return HibernateUtil.getEntityManager().createQuery("""
                FROM Product
                """, Product.class).getResultList();
    }

    public Product findById(Long id) {
        return HibernateUtil.getEntityManager().find(Product.class, id);
    }
}
