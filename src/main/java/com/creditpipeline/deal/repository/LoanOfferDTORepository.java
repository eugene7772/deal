package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.LoanOfferDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanOfferDTORepository extends CrudRepository<LoanOfferDTO, Long> {
}