package com.project.gil.repository;

import com.project.gil.domain.SettleHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettleHistoryRepository extends CrudRepository<SettleHistory, Long> {
}
