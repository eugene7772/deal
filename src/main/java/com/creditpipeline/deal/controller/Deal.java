package com.creditpipeline.deal.controller;

import com.creditpipeline.deal.dto.*;
import com.creditpipeline.deal.entity.Application;
import com.creditpipeline.deal.entity.ApplicationStatusHistory;
import com.creditpipeline.deal.entity.Client;
import com.creditpipeline.deal.entity.LoanOfferDTO;
import com.creditpipeline.deal.enums.Status;
import com.creditpipeline.deal.service.ApplicationService;
import com.creditpipeline.deal.service.ClientService;
import com.creditpipeline.deal.service.ScoringDataService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/deal")
public class Deal {

    private final ClientService clientService;
    private final ApplicationService applicationService;
    private final ScoringDataService scoringDataService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final static Logger logger = LogManager.getLogger(Deal.class);

    @Value("${url}")
    private String url;

    @Value("${url2}")
    private String url2;

    public Deal(ClientService clientService, ApplicationService applicationService, ScoringDataService scoringDataService) {
        this.clientService = clientService;
        this.applicationService = applicationService;
        this.scoringDataService = scoringDataService;
    }

    @PostMapping(value = "/application")
    @Operation(
            summary = "Расчёт возможных условий кредита")
    public List<LoanOfferDTO> getLoanOffers(@RequestBody @Validated LoanApplicationRequestDTO loanApplicationRequestDTO) {
        Client client = new Client(loanApplicationRequestDTO);
        logger.debug("Create client: " + client);

        clientService.addClient(client);
        logger.debug("Save client: " + client.getId() + " " + client.getLastName() + " " + client.getFirstName() + " " + client.getMiddleName() + " " + client.getBirthdate() +
                " " + client.getEmail() + " " + client.getGender() + " " + client.getMaritalStatus() + " " + client.getDependentAmount() + " " + client.getPassportSeries() + " " +
                client.getPassportNumber() + " " + client.getIssueDate() + " " + client.getIssueBranch() + " " + client.getEmploymentStatus() + " " + client.getEmployer() + " " +
                client.getSalary() + " " + client.getPosition() + " " + client.getWorkExperienceCurrent() + " " + client.getWorkExperienceTotal() + " " + client.getAccount());

        Application application = new Application();
        application.setCreationDate(LocalDate.now());
        application.setClient(client);

        applicationService.addApplication(application);

        List<LoanOfferDTO> offers = restTemplate.postForObject(url, loanApplicationRequestDTO, List.class);

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

        Application application = applicationService.getApplicationById(loanOfferDTO.getApplicationId()).orElseThrow();
        logger.debug("Create application" + application.getId() + " " + application.getClient() + " " + application.getCredit() + " " + application.getStatus() +
                " " + application.getCreationDate() + " " + application.getAppliedOffer() + " " + application.getSignDate() + " " + application.getSesCode() + " " + application.getStatusHistory());

        List<ApplicationStatusHistory> history = application.getStatusHistory();
        ApplicationStatusHistory statusHistoryDTO = new ApplicationStatusHistory();
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
    public void completionOfRegistrationAndFullCreditCalculation(@RequestBody @Validated FinishRegistrationRequestDTO finishRegistrationRequestDTO, @PathVariable(value = "applicationId") Long applicationId) {

        Application application = applicationService.getApplicationById(applicationId).orElseThrow();

        ScoringDataDTO scoringDataDTO = scoringDataService.getScoringDataDTO(application, finishRegistrationRequestDTO);

        restTemplate.postForEntity(url2, scoringDataDTO, PostMapping.class);
        logger.debug("Post scoringDataDTO to conveyor: " + scoringDataDTO);

    }

}
