package com.tutrit.hibernate.dao;

import com.tutrit.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {

    private final SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(User person) {
        sessionFactory
                .getCurrentSession()
                .persist(person);
    }
    @Transactional
    public User findById(Long id){
        return sessionFactory
                .getCurrentSession()
                .get(User.class, id);
    }

    @Transactional
    public void deleteById(Long id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(User.class, id));
    }

    @Transactional
    public void update(User user, Long id) {
        Session session = sessionFactory.getCurrentSession();
        User userUpdated = session.get(User.class, id);

        userUpdated.setName(user.getName());
        userUpdated.setPhoneNumber(user.getPhoneNumber());

    }

}
