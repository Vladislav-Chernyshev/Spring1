package ru.chernyshev.homeworklesson6;


import org.springframework.stereotype.Service;
import ru.chernyshev.homeworklesson6.utils.HibernateUtil;

import java.util.List;

@Service
public class OrderService {


    public List<Order> findCustom(Long id){
        return HibernateUtil.getEntityManager().createQuery("""
                FROM Order
                where customId =\040""" + id, Order.class).getResultList();
    }

    public List<Order> findProduct(Long id){
        return HibernateUtil.getEntityManager().createQuery("""
                FROM Order
                where productId =\040""" + id, Order.class).getResultList();
    }

//    public List<Order> detailing(Long id){
//        return HibernateUtil.getEntityManager().createQuery("""
//                SELECT date, name, cost FROM Order o
//                LEFT JOIN Product p ON o.id = p.id
//                WHERE customId =\040""" + id).getResultList();
//
//    }







}
