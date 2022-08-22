package ru.chernyshev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.chernyshev.*;
import ru.chernyshev.persist.*;

@Configuration
public class AppConfiguration {

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl();
    }

    @Bean
    public UserService userService(UserRepository userRepository){
        return new UserService(userRepository);
    }

    @Bean
    public ProductRepositoryImpl productRepository(){
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductService productService(ProductRepositoryImpl productRepository){
        return new ProductService(productRepository);
    }

    @Bean
    @Scope("prototype")
    public CartService cartService(ProductRepositoryImpl productRepository){
        return new CartService(productRepository);
    }
}
