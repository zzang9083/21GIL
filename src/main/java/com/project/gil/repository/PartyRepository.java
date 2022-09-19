package com.project.gil.repository;

import com.project.gil.constant.CommonConstant;
import com.project.gil.domain.Ott;
import com.project.gil.domain.Party;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PartyRepository {

    @PersistenceContext
    EntityManager em;

    public Party findByOttidAndDuration(Ott ott,long duration) {
        return em.createQuery("select p from Party p" +
                        " join fetch p.ott" +
                        " where p.ott = :ott" +
                        " and p.status = :status " +
                        " and p.duration >= :duration", Party.class)
                .setParameter("ott",ott)
                .setParameter("status", CommonConstant.PARTY_MATCHING)
                .setParameter("duration", duration)
                .setMaxResults(1)
                .getResultList()
                .stream().findFirst().orElse(null);

    }

    public void save(Party party) {
        if(party.getId() == null || party.getId() == 0) {
            em.persist(party);
        }
        else {
            em.merge(party);
        }
    }



}
