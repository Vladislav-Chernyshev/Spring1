package ru.chernyshev;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.chernyshev.model.Contact;
import ru.chernyshev.model.ContactType;
import ru.chernyshev.model.Passport;
import ru.chernyshev.model.User;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainLesson6 {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

//
//        Contact mobile = new Contact(ContactType.MOBILE_PHONE, "8252");
//        Contact email = new Contact(ContactType.HOME_EMAIL, "dwasd@mail.gnom");
//        List<Contact> contactList = Arrays.asList(mobile, email);
//
//        Contact mobile2 = new Contact(ContactType.MOBILE_PHONE, "8252");
//        Contact email2 = new Contact(ContactType.HOME_EMAIL, "dwasd@mail.gnom");
//        List<Contact> contactList2 = Arrays.asList(mobile2, email2);
//
//        User user = new User("User1", contactList, "pass1");
//        User user2 = new User("User2", contactList2, "pass2");
//        User user3 = new User("User3", Collections.emptyList(), "pass3");
//        Passport passport = new Passport("12345678", "3232", "RUVD", Instant.now());
//        user3.setPassport(passport);
//
//        entityManager.getTransaction().begin();
//        entityManager.persist(user3);
//        entityManager.getTransaction().commit();
////        entityManager.getTransaction().begin();
////        contactList.forEach(contact -> contact.setUser(user));
////        contactList2.forEach(contact -> contact.setUser(user2));
////        entityManager.persist(user);
////        user2.setId(2L);
////        entityManager.persist(user2);
////        entityManager.getTransaction().commit();
//
////        List<User> users = entityManager.createNamedQuery("findAllUsers", User.class).getResultList();
//
//
//        List<User> users = entityManager.createQuery("select u from User u " +
//                "join fetch u.contacts", User.class).getResultList();
//
//        List<Contact> contacts = users.get(0).getContacts();
//
//
//        for (User user1 : users) {
//            user1.getContacts().forEach(System.out::println);
//        }

        entityManager.getTransaction().begin();
//
//        User user = entityManager.find(User.class, 2L);
//        user.getContacts().remove(0);
//        entityManager.merge(user);


        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }
}
