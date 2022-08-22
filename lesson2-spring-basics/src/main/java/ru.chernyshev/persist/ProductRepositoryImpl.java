package ru.chernyshev.persist;

import org.springframework.stereotype.Repository;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository(value = "ProductRepository")
public class ProductRepositoryImpl implements ProductRepository{

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    public List<Product> findAll(){
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id){
        return productMap.get(id);
    }

    public void insert(Product product){
        long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(product.getId(), product);
    }

    public void update(Product product){
        productMap.put(product.getId(), product);
    }

    public void delete(long id){
        productMap.remove(id);
    }

    public Map<Long, Product> getProductMap() {
        return productMap;
    }
}
