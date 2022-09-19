package com.project.gil.repository;

import com.project.gil.constant.CommonConstant;
import com.project.gil.domain.Wait;
import org.springframework.stereotype.Repository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class WaitRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Wait wait) {
        if(wait.getId() == null) {
            em.persist(wait);
        }
        else {
            em.merge(wait);
        }
    }

    public List<Wait> findUnmatchListByUserId(Long userId) {
        return em.createQuery("select w from Wait w " +
                            "where w.userId = :userId" +
                            " and w.status = :status",Wait.class)
                .setParameter("userId",userId)
                .setParameter("status", CommonConstant.WAIT_ONGOING)
                .getResultList();
    }
}
