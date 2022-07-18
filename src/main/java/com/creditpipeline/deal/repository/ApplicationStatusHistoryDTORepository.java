package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.ApplicationStatusHistoryDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationStatusHistoryDTORepository extends CrudRepository<ApplicationStatusHistoryDTO, Long> {
}
