package com.project.gil.repository;

import com.project.gil.constant.CommonConstant;
import com.project.gil.domain.Match;
import com.project.gil.domain.Party;
import com.project.gil.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MatchRepository {

    @PersistenceContext
    EntityManager em;

    public List<Match> findByUserId(User user) {
        return em.createQuery("select m from Match m " +
                        " join fetch m.party p" +
                        " where  m.user = :user" +
                        " and m.status = :status")
                .setParameter("user",user)
                .setParameter("status",CommonConstant.WAIT_ONGOING)
                .getResultList();
    }

    public List<Match> findByParty(Party party) {
        return em.createQuery("select m from Match m " +
                        " join fetch m.party"+
                        " where m.party = :party")
                .setParameter("party",party)
                .getResultList();
    }

    public void save(Match match) {
        if(match.getId() == null || match.getId() == 0) {
            em.persist(match);
        }
        else {
            em.merge(match);
        }
    }


}
