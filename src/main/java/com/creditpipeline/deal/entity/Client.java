package com.creditpipeline.deal.entity;

import com.creditpipeline.deal.dto.LoanApplicationRequestDTO;
import com.creditpipeline.deal.enums.EmploymentStatus;
import com.creditpipeline.deal.enums.Gender;
import com.creditpipeline.deal.enums.MaritalStatus;
import com.creditpipeline.deal.enums.Position;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthdate;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private Integer dependentAmount;
    private String passportSeries;
    private String passportNumber;
    private LocalDate issueDate;
    private String issueBranch;

    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee")
    private Employment employer;
    private Integer salary;

    @Enumerated(EnumType.STRING)
    private Position position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
    private String account;

    public Client(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        lastName = loanApplicationRequestDTO.getLastName();
        firstName = loanApplicationRequestDTO.getFirstName();
        middleName = loanApplicationRequestDTO.getMiddleName();
        birthdate = loanApplicationRequestDTO.getBirthdate();
        email = loanApplicationRequestDTO.getEmail();
        passportSeries = loanApplicationRequestDTO.getPassportSeries();
        passportNumber = loanApplicationRequestDTO.getPassportNumber();

    }
    public Client(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public Employment getEmployer() {
        return employer;
    }

    public void setEmployer(Employment employer) {
        this.employer = employer;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", maritalStatus=" + maritalStatus +
                ", dependentAmount=" + dependentAmount +
                ", passportSeries='" + passportSeries + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", issueDate=" + issueDate +
                ", issueBranch='" + issueBranch + '\'' +
                ", employmentStatus=" + employmentStatus +
                ", employer=" + employer +
                ", salary=" + salary +
                ", position=" + position +
                ", workExperienceTotal=" + workExperienceTotal +
                ", workExperienceCurrent=" + workExperienceCurrent +
                ", account='" + account + '\'' +
                '}';
    }
public class Client {
}
