package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.EmploymentDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentDTORepository extends CrudRepository<EmploymentDTO, Long> {
}
