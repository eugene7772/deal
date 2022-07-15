package com.creditpipeline.deal.service;

import com.creditpipeline.deal.entity.Application;
import com.creditpipeline.deal.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }

    public Optional<Application> getApplicationById(Long id){
        return applicationRepository.findById(id);
    }

    public void addApplication(Application application){
        applicationRepository.save(application);
    }

}
