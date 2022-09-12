package ru.chernyshev.homeworklesson6;

import org.springframework.stereotype.Repository;
import ru.chernyshev.homeworklesson6.utils.HibernateUtil;

import java.util.List;

@Repository
public class CustomRepository {

    public List<Custom> findAll() {
        return HibernateUtil.getEntityManager().createQuery("""
                FROM Custom
                """, Custom.class).getResultList();
    }

    public Custom findById(Long id) {
        return HibernateUtil.getEntityManager().find(Custom.class, id);
    }
}
