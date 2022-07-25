package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.Credit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository  extends CrudRepository<Credit, Long> {
}
