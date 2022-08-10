package ru.chernyshev;

import ru.chernyshev.products.Product;
import ru.chernyshev.products.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductNumberServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        this.productRepository.insert(new Product("Хлеб", 35.00));
        this.productRepository.insert(new Product("Сыр", 600.00));
        this.productRepository.insert(new Product("Молоко", 100.00));
        this.productRepository.insert(new Product("Кефир", 120.00));
        this.productRepository.insert(new Product("Сигареты", 200.00));
        this.productRepository.insert(new Product("Шоколад", 100.00));
        this.productRepository.insert(new Product("Печенье", 60.00));
        this.productRepository.insert(new Product("Кофе", 200.00));
        this.productRepository.insert(new Product("Картофель", 75.00));
        this.productRepository.insert(new Product("Мука", 80.00));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        String formPathInfo = pathInfo.replaceAll("/", "");
        PrintWriter writer = resp.getWriter();

        if (isNumeric(formPathInfo)) {
            long id = Long.parseLong(formPathInfo);
            Product product = productRepository.findById(id);
            if (product != null) {
                writer.println("<table>");
                writer.println("<tr>");
                writer.println("<th>id</th>");
                writer.println("<th>product</th>");
                writer.println("<th>cost</th>");
                writer.println("</tr>");
                writer.println("<tr>");
                writer.println("<td>" + product.getId() + "</td>");
                writer.println("<td>" + product.getTitle() + "</td>");
                writer.println("<td>" + product.getCost() + "</td>");
                writer.println("</tr>");
                writer.println("</table>");
            } else {
                writer.println("<th>ERROR: 404 PAGE NOT FOUND</th>");
            }
        } else {
            writer.println("<th>ERROR: 404 PAGE NOT FOUND</th>");
        }
    }


    private boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
