package com.creditpipeline.deal.service;

import com.creditpipeline.deal.dto.FinishRegistrationRequestDTO;
import com.creditpipeline.deal.dto.ScoringDataDTO;
import com.creditpipeline.deal.entity.Application;
import org.springframework.stereotype.Service;

@Service
public class ScoringDataService {

    public ScoringDataDTO getScoringDataDTO(Application application, FinishRegistrationRequestDTO finishRegistrationRequestDTO) {

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();

        scoringDataDTO.setAccount(finishRegistrationRequestDTO.getAccount());
        scoringDataDTO.setAmount(application.getCredit().getAmount());
        scoringDataDTO.setTerm(application.getCredit().getTerm());
        scoringDataDTO.setFirstName(application.getClient().getFirstName());
        scoringDataDTO.setMiddleName(application.getClient().getMiddleName());
        scoringDataDTO.setLastName(application.getClient().getLastName());
        scoringDataDTO.setGender(finishRegistrationRequestDTO.getGender());
        scoringDataDTO.setBirthdate(application.getClient().getBirthdate());
        scoringDataDTO.setPassportSeries(application.getClient().getPassportSeries());
        scoringDataDTO.setPassportNumber(application.getClient().getPassportNumber());
        scoringDataDTO.setPassportIssueDate(finishRegistrationRequestDTO.getPassportIssueDate());
        scoringDataDTO.setPassportIssueBranch(finishRegistrationRequestDTO.getPassportIssueBranch());
        scoringDataDTO.setMaritalStatus(finishRegistrationRequestDTO.getMaritalStatus());
        scoringDataDTO.setDependentAmount(finishRegistrationRequestDTO.getDependentAmount());
        scoringDataDTO.setEmployment(finishRegistrationRequestDTO.getEmployment());
        scoringDataDTO.setInsuranceEnabled(application.getCredit().getInsuranceEnabled());
        scoringDataDTO.setSalaryClient(application.getCredit().getSalaryClient());

        return scoringDataDTO;
    }

}
