package ru.chernyshev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.chernyshev.config.AppConfiguration;
import ru.chernyshev.persist.Product;
import ru.chernyshev.persist.User;
import ru.chernyshev.persist.UserRepositoryImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//        UserService userService = context.getBean("userService", UserService.class);
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.println("Enter user name: ");
//            String userName = scanner.nextLine();
//            System.out.println("Enter user role: ");
//            String userRole = scanner.nextLine();
//            userService.insert(new User(userName, userRole));
//
//            System.out.println("New User has been added. Current users count: " + userService.findAll());
//            System.out.println("Enter \"end\" to exit");
//            if (scanner.nextLine().equals("end")) {
//                return;
//            }
//
//        }

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ProductService productService = context.getBean("productService", ProductService.class);
        CartService cartService = context.getBean("cartService", CartService.class);
        productService.insert(new Product("Хлеб", 45.00));
        productService.insert(new Product("Молоко", 90.00));
        productService.insert(new Product("Шоколад", 100.00));
        productService.insert(new Product("Мясо", 350.00));
        productService.insert(new Product("Рыба", 300.00));

        Scanner scanner = new Scanner(System.in);
        while (true){
            choice();
            int number = scanner.nextInt();
            switch (number) {
                case (1) -> {
                    System.out.println("Введите ID товара для добавления в корзину: ");
                    int id1 = scanner.nextInt();

                    if (isItPossibleToAdd(cartService, id1)){
                        cartService.insert(id1);
                        System.out.println("Товар добавлен в корзину.");
                        System.out.println("--------------------------");
                    } else {
                        System.out.println("Данного товара нет в наличии, выберите другой товар.");
                        System.out.println("--------------------------");
                    }
                }
                case (2) -> {
                    System.out.println("Введите ID товара для удаления из корзины: ");
                    int id2 = scanner.nextInt();
                    if (cartService.findById(id2) == null){
                        System.out.println("Данного товара нет в корзине.");
                    }else {
                        cartService.delete(id2);
                        System.out.println("Товар удален из корзины.");
                        System.out.println("--------------------------");
                    }
                }
            }
            if (number == 3){
                System.out.println("Вы закончили работу с корзиной.");
                System.out.println("--------------------------");
                break;
            }
        }


    }

    private static boolean isItPossibleToAdd(CartService cartService, long id){
        try {
            cartService.insert(id);
            return true;

        }catch (NullPointerException e){
            return false;
        }
    }

    private static void choice() {
        System.out.println("Если вы хотите добавить товар в корзину введите цифру 1.");
        System.out.println("Если вы хотите удалить товар из корзины введите цифру 2.");
        System.out.println("Прекратить работу с корзиной введите цифру 3.");
    }


}

