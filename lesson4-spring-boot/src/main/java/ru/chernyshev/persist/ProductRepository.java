package ru.chernyshev.persist;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.insert(new Product("Bread", 20.00, 5));
        this.insert(new Product("Bread1", 30.00, 3));
        this.insert(new Product("Bread2", 40.00, 6));
        this.insert(new Product("Bread3", 50.00, 1));
        this.insert(new Product("Bread4", 60.00, 6));
    }

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(product.getId(), product);
    }

    public void addAndUpdate(Product product) {
        if (product.getId() == null) {
            long id = identity.incrementAndGet();
            product.setId(id);
            productMap.put(id, product);
        } else {
            productMap.put(product.getId(), product);
        }
    }

    public void delete(long id) {
        productMap.remove(id);
    }

    public Map<Long, Product> getProductMap() {
        return productMap;
    }
}
