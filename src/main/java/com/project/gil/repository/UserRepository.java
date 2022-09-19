package com.project.gil.repository;

import com.project.gil.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    EntityManager em;

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

    public void save(User user) {
        em.persist(user);
    }

}
