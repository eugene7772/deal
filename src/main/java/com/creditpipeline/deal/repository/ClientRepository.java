package com.creditpipeline.deal.repository;

import com.creditpipeline.deal.entity.Client;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Qualifier("ClientRepository")
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
