package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.ApplicationStatusHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationStatusHistoryRepository extends CrudRepository<ApplicationStatusHistory, Long> {
}
