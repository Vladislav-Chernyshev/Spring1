package ru.chernyshev;

import ru.chernyshev.persist.Product;
import ru.chernyshev.persist.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();
        productRepository.insert(new Product("Хлеб", 35.00));
        productRepository.insert(new Product("Сыр", 600.00));
        productRepository.insert(new Product("Молоко", 100.00));
        productRepository.insert(new Product("Кефир", 120.00));
        productRepository.insert(new Product("Сигареты", 200.00));
        productRepository.insert(new Product("Шоколад", 100.00));
        productRepository.insert(new Product("Печенье", 60.00));
        productRepository.insert(new Product("Кофе", 200.00));
        productRepository.insert(new Product("Картофель", 75.00));
        productRepository.insert(new Product("Мука", 80.00));
        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
