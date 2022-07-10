package com.creditpipeline.deal.controller;

import com.creditpipeline.deal.dto.FinishRegistrationRequestDTO;
import com.creditpipeline.deal.dto.LoanApplicationRequestDTO;
import com.creditpipeline.deal.dto.LoanOfferDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/deal")
public class Deal {

    @PostMapping(value = "/application")
    @Operation(
            summary = "Расчёт возможных условий кредита")
    public List<LoanOfferDTO> getLoanOffers (@RequestBody LoanApplicationRequestDTO loanApplicationRequestDTO){
        List<LoanOfferDTO> offers = new ArrayList<>();
        return offers;
    }

    @PutMapping(value = "/offer")
    @Operation(
            summary = "Выбор одного из предложений")
    public void choiceOffer (@RequestBody LoanOfferDTO loanOfferDTO){

    }

    @PutMapping(value = "/calculate/{applicationId}")
    @Operation(
            summary = "Завершение регистрации + полный подсчёт кредита")
    public void completionOfRegistrationAndFullCreditCalculation (@RequestBody FinishRegistrationRequestDTO finishRegistrationRequestDTO, @PathVariable(value = "id") Long applicationId){

    }

}
