package controller;

import com.creditpipeline.deal.controller.Deal;
import com.creditpipeline.deal.dto.FinishRegistrationRequestDTO;
import com.creditpipeline.deal.dto.LoanApplicationRequestDTO;
import com.creditpipeline.deal.dto.ScoringDataDTO;
import com.creditpipeline.deal.entity.*;
import com.creditpipeline.deal.enums.*;
import com.creditpipeline.deal.exception.ApplicationException;
import com.creditpipeline.deal.service.ApplicationService;
import com.creditpipeline.deal.service.ClientService;
import com.creditpipeline.deal.service.ScoringDataService;
import liquibase.pro.packaged.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class DealTest {

    @Mock
    private ApplicationService applicationService;

    @Mock
    private ClientService clientService;

    @Mock
    private ScoringDataService scoringDataService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private Deal deal;

    @Test
    public void getLoanOffersTest() {

        String url = "http://localhost:8087/conveyor/offers";

        LoanApplicationRequestDTO loanApplicationRequestDTO = new LoanApplicationRequestDTO();
        loanApplicationRequestDTO.setAmount(BigDecimal.valueOf(500000));
        loanApplicationRequestDTO.setTerm(18);
        loanApplicationRequestDTO.setFirstName("Ivan");
        loanApplicationRequestDTO.setLastName("Ivanov");
        loanApplicationRequestDTO.setMiddleName("Ivanovich");
        loanApplicationRequestDTO.setEmail("ivanov@mail.com");
        loanApplicationRequestDTO.setBirthdate(LocalDate.of(2000,01,01));
        loanApplicationRequestDTO.setPassportSeries("2014");
        loanApplicationRequestDTO.setPassportNumber("289543");

        ResponseEntity<List<LoanOfferDTO>> offers2 = new ResponseEntity<List<LoanOfferDTO>>(HttpStatus.ACCEPTED);

        Mockito.when(restTemplate.exchange(url, HttpMethod.POST, ResponseEntity.ok(loanApplicationRequestDTO) , new ParameterizedTypeReference<List<LoanOfferDTO>>() {}))
                .thenReturn(offers2);
        List<LoanOfferDTO> offers = deal.getLoanOffers(ResponseEntity.ok(loanApplicationRequestDTO));
        Assertions.assertEquals(ResponseEntity.ok(offers2).getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(ResponseEntity.ok(offers).getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void choiceOfferTest() {

        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        loanOfferDTO.setApplicationId(3l);
        loanOfferDTO.setTerm(18);
        loanOfferDTO.setApplicationId(0l);
        loanOfferDTO.setRequestedAmount(BigDecimal.valueOf(500000));
        loanOfferDTO.setTotalAmount(BigDecimal.valueOf(500000));

        Application application = new Application();

        List<ApplicationStatusHistory> history = new ArrayList<>();
        ApplicationStatusHistory statusHistoryDTO = new ApplicationStatusHistory();
        statusHistoryDTO.setStatus(Status.APPROVED);
        statusHistoryDTO.setTime(LocalDate.now());
        history.add(statusHistoryDTO);
        application.setStatusHistory(history);
        application.setStatus(Status.APPROVED);
        application.setAppliedOffer(loanOfferDTO);

        Mockito.when(applicationService.getApplicationById(loanOfferDTO.getApplicationId())).thenReturn(java.util.Optional.of(application));
        deal.choiceOffer(loanOfferDTO);

        Mockito.verify(applicationService).addApplication(application);

    }

    @Test
    public void completionOfRegistrationAndFullCreditCalculationTest() {

        String url2 = "http://localhost:8087/conveyor/calculation";

        LoanOfferDTO offer = new LoanOfferDTO();
        offer.setRequestedAmount(BigDecimal.valueOf(10000));
        offer.setSalaryClient(true);
        offer.setInsuranceEnabled(true);
        offer.setTerm(6);

        List<PaymentScheduleElement> paymentScheduleElements = new ArrayList<>();

        PaymentScheduleElement paymentScheduleElement1 = new PaymentScheduleElement();
        paymentScheduleElement1.setNumber(1);
        paymentScheduleElement1.setDate(LocalDate.of(2022,8,14));
        paymentScheduleElement1.setTotalPayment(BigDecimal.valueOf(1696).setScale(3));
        paymentScheduleElement1.setInterestPayment(BigDecimal.valueOf(50.9589));
        paymentScheduleElement1.setDebtPayment(BigDecimal.valueOf(1645.0411));
        paymentScheduleElement1.setRemainingDebt(BigDecimal.valueOf(8354.9589));
        paymentScheduleElements.add(paymentScheduleElement1);

        PaymentScheduleElement paymentScheduleElement2 = new PaymentScheduleElement();
        paymentScheduleElement2.setNumber(2);
        paymentScheduleElement2.setDate(LocalDate.of(2022,9,14));
        paymentScheduleElement2.setTotalPayment(BigDecimal.valueOf(1696).setScale(3));
        paymentScheduleElement2.setInterestPayment(BigDecimal.valueOf(41.20254));
        paymentScheduleElement2.setDebtPayment(BigDecimal.valueOf(1654.79746));
        paymentScheduleElement2.setRemainingDebt(BigDecimal.valueOf(6700.16144));
        paymentScheduleElements.add(paymentScheduleElement2);

        PaymentScheduleElement paymentScheduleElement3 = new PaymentScheduleElement();
        paymentScheduleElement3.setNumber(3);
        paymentScheduleElement3.setDate(LocalDate.of(2022,10,14));
        paymentScheduleElement3.setTotalPayment(BigDecimal.valueOf(1696).setScale(3));
        paymentScheduleElement3.setInterestPayment(BigDecimal.valueOf(34.14329));
        paymentScheduleElement3.setDebtPayment(BigDecimal.valueOf(1661.85671));
        paymentScheduleElement3.setRemainingDebt(BigDecimal.valueOf(5038.30473));
        paymentScheduleElements.add(paymentScheduleElement3);

        PaymentScheduleElement paymentScheduleElement4 = new PaymentScheduleElement();
        paymentScheduleElement4.setNumber(4);
        paymentScheduleElement4.setDate(LocalDate.of(2022,11,14));
        paymentScheduleElement4.setTotalPayment(BigDecimal.valueOf(1696).setScale(3));
        paymentScheduleElement4.setInterestPayment(BigDecimal.valueOf(24.84643));
        paymentScheduleElement4.setDebtPayment(BigDecimal.valueOf(1671.15357));
        paymentScheduleElement4.setRemainingDebt(BigDecimal.valueOf(3367.15116));
        paymentScheduleElements.add(paymentScheduleElement4);

        PaymentScheduleElement paymentScheduleElement5 = new PaymentScheduleElement();
        paymentScheduleElement5.setNumber(5);
        paymentScheduleElement5.setDate(LocalDate.of(2022,12,14));
        paymentScheduleElement5.setTotalPayment(BigDecimal.valueOf(1696).setScale(3));
        paymentScheduleElement5.setInterestPayment(BigDecimal.valueOf(17.15863));
        paymentScheduleElement5.setDebtPayment(BigDecimal.valueOf(1678.84137));
        paymentScheduleElement5.setRemainingDebt(BigDecimal.valueOf(1688.30979));
        paymentScheduleElements.add(paymentScheduleElement5);

        PaymentScheduleElement paymentScheduleElement6 = new PaymentScheduleElement();
        paymentScheduleElement6.setNumber(6);
        paymentScheduleElement6.setDate(LocalDate.of(2023,1,14));
        paymentScheduleElement6.setTotalPayment(BigDecimal.valueOf(1696).setScale(3));
        paymentScheduleElement6.setInterestPayment(BigDecimal.valueOf(8.60344));
        paymentScheduleElement6.setDebtPayment(BigDecimal.valueOf(1687.39656));
        paymentScheduleElement6.setRemainingDebt(BigDecimal.valueOf(0.91323));
        paymentScheduleElements.add(paymentScheduleElement6);

        Employment employment = new Employment();
        employment.setEmploymentStatus(EmploymentStatus.EMPLOYED);
        employment.setId(1l);
        employment.setEmployerINN("2312451251");
        employment.setPosition(Position.OWNER);
        employment.setSalary(BigDecimal.valueOf(32312));
        employment.setWorkExperienceCurrent(12);
        employment.setWorkExperienceTotal(12);

        Client client = new Client();
        client.setIssueDate(LocalDate.now());
        client.setPassportNumber("321421");
        client.setId(0l);
        client.setFirstName("Ivan");
        client.setLastName("Ivanov");
        client.setMiddleName("Ivanovich");
        client.setBirthdate(LocalDate.of(2000,01,01));
        client.setEmail("ivanov@mail.com");
        client.setGender(Gender.MALE);
        client.setMaritalStatus(MaritalStatus.MARRIED);
        client.setDependentAmount(500000);
        client.setPassportSeries("2013");
        client.setIssueDate(LocalDate.of(2014,01,01));
        client.setIssueBranch("Vrn");
        client.setEmploymentStatus(EmploymentStatus.EMPLOYED);
        client.setEmployer(employment);
        client.setSalary(300000);
        client.setPosition(Position.OWNER);
        client.setWorkExperienceCurrent(12);
        client.setWorkExperienceTotal(12);

        Credit credit = new Credit();
        credit.setId(4l);
        credit.setAmount(BigDecimal.valueOf(10000));
        credit.setTerm(6);
        credit.setMonthlyPayment(1696);
        credit.setRate(10);
        credit.setPsk(5);
        credit.setPaymentSchedule(paymentScheduleElements);
        credit.setInsuranceEnabled(true);
        credit.setSalaryClient(true);
        credit.setCreditStatus(CreditStatus.CALCULATED);

        FinishRegistrationRequestDTO finishRegistrationRequestDTO = new FinishRegistrationRequestDTO();
        finishRegistrationRequestDTO.setAccount("1");
        finishRegistrationRequestDTO.setGender(Gender.MALE);
        finishRegistrationRequestDTO.setMaritalStatus(MaritalStatus.MARRIED);
        finishRegistrationRequestDTO.setDependentAmount(500000);
        finishRegistrationRequestDTO.setPassportIssueDate(LocalDate.of(2014,6,21));
        finishRegistrationRequestDTO.setPassportIssueBranch("Vrn");
        finishRegistrationRequestDTO.setEmployment(employment);

        List<ApplicationStatusHistory> history = new ArrayList<>();
        ApplicationStatusHistory statusHistoryDTO = new ApplicationStatusHistory();
        statusHistoryDTO.setStatus(Status.APPROVED);
        statusHistoryDTO.setTime(LocalDate.now());
        history.add(statusHistoryDTO);

        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setAmount(BigDecimal.valueOf(10000));
        scoringDataDTO.setTerm(6);
        scoringDataDTO.setFirstName("Ivan");
        scoringDataDTO.setMiddleName("Ivanovich");
        scoringDataDTO.setLastName("Ivanov");
        scoringDataDTO.setGender(Gender.MALE);
        scoringDataDTO.setBirthdate(LocalDate.of(2000,01,01));
        scoringDataDTO.setPassportIssueDate(LocalDate.of(2014,01,01));
        scoringDataDTO.setPassportSeries("23124");
        scoringDataDTO.setPassportNumber("2w321");
        scoringDataDTO.setMaritalStatus(MaritalStatus.MARRIED);
        scoringDataDTO.setDependentAmount(123123);
        scoringDataDTO.setEmployment(employment);
        scoringDataDTO.setAccount("213123");
        scoringDataDTO.setInsuranceEnabled(true);
        scoringDataDTO.setSalaryClient(true);

        Application application = new Application();
        application.setId(4l);
        application.setClient(client);
        application.setCredit(credit);
        application.setStatus(Status.APPROVED);
        application.setCreationDate(LocalDate.now());
        application.setAppliedOffer(offer);
        application.setSignDate(LocalDate.now());
        application.setSesCode("231");
        application.setStatusHistory(history);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity(scoringDataDTO.toString(), headers);

        Mockito.when(applicationService.getApplicationById(4l).orElseThrow(() -> new ApplicationException("App exception"))).thenReturn(application);
        Mockito.when(scoringDataService.getScoringDataDTO(application, finishRegistrationRequestDTO)).thenReturn(scoringDataDTO);
        Mockito.when(restTemplate.exchange(url2, HttpMethod.POST, entity , new ParameterizedTypeReference<Credit>() {})).thenReturn((ResponseEntity.ok(credit)));

        deal.completionOfRegistrationAndFullCreditCalculation(finishRegistrationRequestDTO,1l);

    }

}
