package com.creditpipeline.deal.controller;

import com.creditpipeline.deal.dto.*;
import com.creditpipeline.deal.entity.Application;
import com.creditpipeline.deal.entity.Client;
import com.creditpipeline.deal.enums.Status;
import com.creditpipeline.deal.service.ApplicationService;
import com.creditpipeline.deal.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/deal")
public class Deal {

    private final ClientService clientService;
    private final ApplicationService applicationService;
    private final RestTemplate restTemplate;
    private final static Logger logger = LogManager.getLogger(Deal.class);
    private final static String url = "http://localhost:8087/conveyor/offers";
    private final static String url2 = "http://localhost:8087/conveyor/calculation";

    public Deal(ClientService clientService, ApplicationService applicationService, RestTemplate restTemplate) {
        this.clientService = clientService;
        this.applicationService = applicationService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/application")
    @Operation(
            summary = "Расчёт возможных условий кредита")
    public List<LoanOfferDTO> getLoanOffers(@RequestBody @Validated LoanApplicationRequestDTO loanApplicationRequestDTO) {
        List<LoanOfferDTO> offers = new ArrayList<>();
        logger.debug("create offers: " + offers);
        Client client = new Client(loanApplicationRequestDTO);
        logger.debug("Create client: " + client);

        clientService.addClient(client);
        logger.debug("Save client: " + client.getId() + " " + client.getLastName() + " " + client.getFirstName() + " " + client.getMiddleName() + " " + client.getBirthdate() +
                " " + client.getEmail() + " " + client.getGender() + " " + client.getMaritalStatus() + " " + client.getDependentAmount() + " " + client.getPassportSeries() + " " +
                client.getPassportNumber() + " " + client.getIssueDate() + " " + client.getIssueBranch() + " " + client.getEmploymentStatus() + " " + client.getEmployer() + " " +
                client.getSalary() + " " + client.getPosition() + " " + client.getWorkExperienceCurrent() + " " + client.getWorkExperienceTotal() + " " + client.getAccount());

        Application application = new Application();
        application.setCreationDate(LocalDate.now());
        applicationService.addApplication(application);

        restTemplate.postForObject(url, offers, PostMapping.class);

        for (LoanOfferDTO offerDTO : offers) {
            offerDTO.setApplicationId(application.getId());
        }

        logger.debug("Return offers: " + offers);
        return offers;
    }

    @PutMapping(value = "/offer")
    @Operation(
            summary = "Выбор одного из предложений")
    public void choiceOffer(@RequestBody @Validated LoanOfferDTO loanOfferDTO) {

        Application application = applicationService.getApplicationById(loanOfferDTO.getApplicationId()).get();
        logger.debug("Create application" + application.getId() + " " + application.getClient() + " " + application.getCredit() + " " + application.getStatus() +
                " " + application.getCreationDate() + " " + application.getAppliedOffer() + " " + application.getSignDate() + " " + application.getSesCode() + " " + application.getStatusHistory());
        List<ApplicationStatusHistoryDTO> history = application.getStatusHistory();
        ApplicationStatusHistoryDTO statusHistoryDTO = new ApplicationStatusHistoryDTO();
        statusHistoryDTO.setStatus(Status.APPROVED);
        history.add(statusHistoryDTO);
        application.setStatusHistory(history);
        application.setStatus(Status.APPROVED);
        application.setAppliedOffer(loanOfferDTO);

        applicationService.addApplication(application);

    }

    @PutMapping(value = "/calculate/{applicationId}")
    @Operation(
            summary = "Завершение регистрации + полный подсчёт кредита")
    public void completionOfRegistrationAndFullCreditCalculation(@RequestBody @Validated FinishRegistrationRequestDTO finishRegistrationRequestDTO, @PathVariable(value = "id") Long applicationId) {

        Application application = applicationService.getApplicationById(applicationId).get();

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
        scoringDataDTO.setEmploymentDTO(finishRegistrationRequestDTO.getEmploymentDTO());
        scoringDataDTO.setInsuranceEnabled(application.getCredit().getInsuranceEnabled());
        scoringDataDTO.setSalaryClient(application.getCredit().getSalaryClient());

        restTemplate.postForEntity(url2, scoringDataDTO, PostMapping.class);
        logger.debug("Post scoringDataDTO to conveyor: " + scoringDataDTO);

    }

}