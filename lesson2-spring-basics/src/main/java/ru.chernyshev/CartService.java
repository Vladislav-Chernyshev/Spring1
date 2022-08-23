package ru.chernyshev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chernyshev.persist.Product;
import ru.chernyshev.persist.ProductRepository;
import ru.chernyshev.persist.ProductRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CartService {
    private final ProductRepositoryImpl productRepository;

    @Autowired
    public CartService(ProductRepositoryImpl productRepository) {
        this.productRepository = productRepository;
    }
    private final Map<Long, Product> cartMap = new ConcurrentHashMap<>();

    public void insert(long idProduct){
        cartMap.put(idProduct, productRepository.getProductMap().get(idProduct));
    }

    public void delete(long id){
        cartMap.remove(id);
    }

    public Product findById(long id){
        return cartMap.get(id);
    }






}
