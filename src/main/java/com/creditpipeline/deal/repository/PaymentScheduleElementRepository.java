package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.PaymentScheduleElement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentScheduleElementRepository extends CrudRepository<PaymentScheduleElement, Long> {
}
