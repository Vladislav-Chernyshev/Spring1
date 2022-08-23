package ru.chernyshev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.chernyshev.persist.Product;
import ru.chernyshev.persist.ProductRepositoryImpl;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepositoryImpl productRepository;



    public void insert(Product product){
        productRepository.insert(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public String find(long id){

       return productRepository.findById(id).toString();
    }


}
