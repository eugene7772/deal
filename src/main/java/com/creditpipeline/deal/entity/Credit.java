package com.creditpipeline.deal.entity;

import com.creditpipeline.deal.enums.CreditStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id_credit")
    private Long id;
    private BigDecimal amount;
    private Integer term;
    private Integer monthlyPayment;
    private Integer rate;
    private Integer psk;

    @Column
    @ElementCollection(targetClass = PaymentScheduleElement.class)
    private List<PaymentScheduleElement> paymentSchedule;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;
    private CreditStatus creditStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public List<PaymentScheduleElement> getPaymentSchedule() {
        return paymentSchedule;
    }

    public void setPaymentSchedule(List<PaymentScheduleElement> paymentSchedule) {
        this.paymentSchedule = paymentSchedule;
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

    public CreditStatus getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(CreditStatus creditStatus) {
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
                ", paymentSchedule=" + paymentSchedule +
                ", isInsuranceEnabled=" + isInsuranceEnabled +
                ", isSalaryClient=" + isSalaryClient +
                ", creditStatus=" + creditStatus +
                '}';
    }
}
