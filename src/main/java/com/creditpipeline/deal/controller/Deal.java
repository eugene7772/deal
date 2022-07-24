package com.creditpipeline.deal.controller;

import com.creditpipeline.deal.dto.*;
import com.creditpipeline.deal.entity.*;
import com.creditpipeline.deal.enums.Status;
import com.creditpipeline.deal.exception.ApplicationException;
import com.creditpipeline.deal.service.ApplicationService;
import com.creditpipeline.deal.service.ClientService;
import com.creditpipeline.deal.service.ScoringDataService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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
    private final ScoringDataService scoringDataService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final static Logger logger = LogManager.getLogger(Deal.class);

    @Value("${url}")
    private String url = "http://localhost:8087/conveyor/offers";

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
    public List<LoanOfferDTO> getLoanOffers(@RequestBody @Validated ResponseEntity<LoanApplicationRequestDTO> loanApplicationRequestDTO) {
        Client client = new Client(loanApplicationRequestDTO.getBody());
        logger.debug("Create client: " + client);

        clientService.addClient(client);
        logger.debug("Save client: " + client);

        Application application = new Application();
        application.setId(0l);
        application.setCreationDate(LocalDate.now());
        application.setClient(client);

        applicationService.addApplication(application);

        ResponseEntity<List<LoanOfferDTO>> responseOffers = restTemplate.exchange(url, HttpMethod.POST, loanApplicationRequestDTO , new ParameterizedTypeReference<List<LoanOfferDTO>>() {});
        List<LoanOfferDTO> offers = responseOffers.getBody();

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

        Application application = applicationService.getApplicationById(loanOfferDTO.getApplicationId()).orElseThrow(() -> new ApplicationException("App exception"));
        logger.debug("Create application: " + application);

        List<ApplicationStatusHistory> history = new ArrayList<>();
        ApplicationStatusHistory statusHistoryDTO = new ApplicationStatusHistory();
        statusHistoryDTO.setStatus(Status.APPROVED);
        statusHistoryDTO.setTime(LocalDate.now());
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

        Application application = applicationService.getApplicationById(applicationId).orElseThrow(() -> new ApplicationException("App exception"));

        ScoringDataDTO scoringDataDTO = scoringDataService.getScoringDataDTO(application, finishRegistrationRequestDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(scoringDataDTO.toString(), headers);

        ResponseEntity<Credit> credit = restTemplate.exchange(url2, HttpMethod.POST, entity , new ParameterizedTypeReference<Credit>() {});
        credit.getBody();
        logger.debug("Post scoringDataDTO to conveyor: " + scoringDataDTO);

    }

}
