package com.tutrit.hibernate.repository;

import com.tutrit.hibernate.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryHibernate {
    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private User user;
    private static final String UNIT_NAME = "PERSISTENCE";

    private static void start() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(UNIT_NAME);
        }
        entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
    }

    private static void finish() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public User save(User user) {
        start();
        entityManager.persist(user);
        user = new User(user.getUserId(), user.getName(), user.getPhoneNumber());
        finish();
        return user;
    }

    public User findById(Long id) {
        start();
        user = entityManager.find(User.class, id);
        finish();
        return user;
    }

    public void update(User user, Long id) {
        start();

        User foundUser = entityManager.find(User.class, id);
        foundUser.setName(user.getName());
        foundUser.setPhoneNumber(user.getPhoneNumber());

        finish();
    }

    public void deleteById(Long id) {
        start();

        User foundUser = entityManager.find(User.class, id);
        entityManager.remove(foundUser);

        finish();

    }

}
