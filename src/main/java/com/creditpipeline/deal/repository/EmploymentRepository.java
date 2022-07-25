package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.Employment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentRepository extends CrudRepository<Employment, Long> {
}
