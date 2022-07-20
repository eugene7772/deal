package controller;

import com.creditpipeline.deal.controller.Deal;
import com.creditpipeline.deal.dto.FinishRegistrationRequestDTO;
import com.creditpipeline.deal.dto.LoanApplicationRequestDTO;
import com.creditpipeline.deal.entity.Employment;
import com.creditpipeline.deal.entity.LoanOfferDTO;
import com.creditpipeline.deal.enums.EmploymentStatus;
import com.creditpipeline.deal.enums.Gender;
import com.creditpipeline.deal.enums.MaritalStatus;
import com.creditpipeline.deal.enums.Position;
import com.creditpipeline.deal.service.ApplicationService;
import com.creditpipeline.deal.service.ClientService;
import com.creditpipeline.deal.service.ScoringDataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

        List<LoanOfferDTO> offers = restTemplate.postForObject(url, loanApplicationRequestDTO, List.class);

    }

    @Test
    public void choiceOfferTest() {

        LoanOfferDTO loanOfferDTO = new LoanOfferDTO();
        loanOfferDTO.setTerm(18);
        loanOfferDTO.setApplicationId(0l);
        loanOfferDTO.setRequestedAmount(BigDecimal.valueOf(500000));
        loanOfferDTO.setTotalAmount(BigDecimal.valueOf(500000));

        deal.choiceOffer(loanOfferDTO);

    }

    @Test
    public void completionOfRegistrationAndFullCreditCalculationTest() {

        Employment employment = new Employment();
        employment.setEmploymentStatus(EmploymentStatus.EMPLOYED);
        employment.setId(1l);
        employment.setEmployerINN("2312451251");
        employment.setPosition(Position.OWNER);
        employment.setSalary(BigDecimal.valueOf(32312));
        employment.setWorkExperienceCurrent(12);
        employment.setWorkExperienceTotal(12);

        FinishRegistrationRequestDTO finishRegistrationRequestDTO = new FinishRegistrationRequestDTO();
        finishRegistrationRequestDTO.setAccount("1");
        finishRegistrationRequestDTO.setGender(Gender.MALE);
        finishRegistrationRequestDTO.setMaritalStatus(MaritalStatus.MARRIED);
        finishRegistrationRequestDTO.setDependentAmount(500000);
        finishRegistrationRequestDTO.setPassportIssueDate(LocalDate.of(2014,6,21));
        finishRegistrationRequestDTO.setPassportIssueBranch("Vrn");
        finishRegistrationRequestDTO.setEmployment(employment);

        deal.completionOfRegistrationAndFullCreditCalculation(finishRegistrationRequestDTO,1l);

    }


}
