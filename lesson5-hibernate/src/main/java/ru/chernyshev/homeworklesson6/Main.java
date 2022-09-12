package ru.chernyshev.homeworklesson6;

import ru.chernyshev.homeworklesson6.utils.HibernateUtil;

import java.util.List;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        CustomRepository customRepository = new CustomRepository();
        ProductRepository productRepository = new ProductRepository();
        OrderService orderService = new OrderService();

        System.out.println(customRepository.findAll());
        System.out.println(productRepository.findAll());
        System.out.println(orderService.findCustom(1L));
        System.out.println(orderService.findProduct(1L));
        System.out.println(customRepository.findById(1L));
        System.out.println(productRepository.findById(1L));
//        System.out.println(orderService.detailing(1L));



        HibernateUtil.shutdown();

    }
}
