package com.creditpipeline.deal.entity;

import com.creditpipeline.deal.enums.MaritalStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id_client")
    private Long id;
    private String last_name;
    private String first_name;
    private String middle_name;
    private LocalDate birth_date;
    private String email;
    private String gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private String passportSeries;
    private String passportNumber;
    private LocalDate issueDate;
    private String issueBranch;
    private String employmentStatus;
    private String employer;
    private Integer salary;
    private String position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
    private Integer account;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueBranch() {
        return issueBranch;
    }

    public void setIssueBranch(String issueBranch) {
        this.issueBranch = issueBranch;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getWorkExperienceTotal() {
        return workExperienceTotal;
    }

    public void setWorkExperienceTotal(Integer workExperienceTotal) {
        this.workExperienceTotal = workExperienceTotal;
    }

    public Integer getWorkExperienceCurrent() {
        return workExperienceCurrent;
    }

    public void setWorkExperienceCurrent(Integer workExperienceCurrent) {
        this.workExperienceCurrent = workExperienceCurrent;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", middle_name='" + middle_name + '\'' +
                ", birth_date=" + birth_date +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", dependentAmount=" + dependentAmount +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", issueDate=" + issueDate +
                ", issueBranch='" + issueBranch + '\'' +
                ", employmentStatus='" + employmentStatus + '\'' +
                ", employer='" + employer + '\'' +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                ", workExperienceTotal=" + workExperienceTotal +
                ", workExperienceCurrent=" + workExperienceCurrent +
                ", account=" + account +
                '}';
    }
}
