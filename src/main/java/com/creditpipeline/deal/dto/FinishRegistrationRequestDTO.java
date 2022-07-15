package com.creditpipeline.deal.dto;

import com.creditpipeline.deal.enums.Gender;
import com.creditpipeline.deal.enums.MaritalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Завершение регистрации")
public class FinishRegistrationRequestDTO {

    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;
    private EmploymentDTO employmentDTO;
    private String account;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getDependentAmount() {
        return dependentAmount;
    }

    public void setDependentAmount(Integer dependentAmount) {
        this.dependentAmount = dependentAmount;
    }

    public LocalDate getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDate passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportIssueBranch() {
        return passportIssueBranch;
    }

    public void setPassportIssueBranch(String passportIssueBranch) {
        this.passportIssueBranch = passportIssueBranch;
    }

    public EmploymentDTO getEmploymentDTO() {
        return employmentDTO;
    }

    public void setEmploymentDTO(EmploymentDTO employmentDTO) {
        this.employmentDTO = employmentDTO;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "FinishRegistrationRequestDTO{" +
                "gender=" + gender +
                ", maritalStatus=" + maritalStatus +
                ", dependentAmount=" + dependentAmount +
                ", passportIssueDate=" + passportIssueDate +
                ", passportIssueBranch='" + passportIssueBranch + '\'' +
                ", employmentDTO=" + employmentDTO +
                ", account='" + account + '\'' +
                '}';
    }
}
