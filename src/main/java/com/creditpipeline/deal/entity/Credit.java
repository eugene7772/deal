package com.creditpipeline.deal.entity;

import javax.persistence.*;

@Entity
@Table(name="credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id_credit")
    private Long id;
    private Integer amount;
    private Integer term;
    private Integer monthlyPayment;
    private Integer rate;
    private Integer psk;
    private String payment_schedule;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;
    private String creditStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Integer monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getPsk() {
        return psk;
    }

    public void setPsk(Integer psk) {
        this.psk = psk;
    }

    public String getPayment_schedule() {
        return payment_schedule;
    }

    public void setPayment_schedule(String payment_schedule) {
        this.payment_schedule = payment_schedule;
    }

    public Boolean getInsuranceEnabled() {
        return isInsuranceEnabled;
    }

    public void setInsuranceEnabled(Boolean insuranceEnabled) {
        isInsuranceEnabled = insuranceEnabled;
    }

    public Boolean getSalaryClient() {
        return isSalaryClient;
    }

    public void setSalaryClient(Boolean salaryClient) {
        isSalaryClient = salaryClient;
    }

    public String getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(String creditStatus) {
        this.creditStatus = creditStatus;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", amount=" + amount +
                ", term=" + term +
                ", monthlyPayment=" + monthlyPayment +
                ", rate=" + rate +
                ", psk=" + psk +
                ", payment_schedule='" + payment_schedule + '\'' +
                ", isInsuranceEnabled=" + isInsuranceEnabled +
                ", isSalaryClient=" + isSalaryClient +
                ", creditStatus='" + creditStatus + '\'' +
                '}';
    }
}
