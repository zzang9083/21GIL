package com.project.gil.repository;

import com.project.gil.domain.Ott;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OttRepository {

    @PersistenceContext
    EntityManager em;

    public Ott findOne(Long id) {
        return em.find(Ott.class, id);
    }

}
