package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.Application;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Long> {
}
