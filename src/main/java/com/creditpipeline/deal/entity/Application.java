package com.creditpipeline.deal.entity;

import com.creditpipeline.deal.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id_application")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_credit")
    private Credit credit;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
    private LocalDate creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_offer")
    private LoanOfferDTO appliedOffer;

    private LocalDate signDate;
    private String sesCode;

    @Column
    @ElementCollection(targetClass = ApplicationStatusHistoryDTO.class)
    private List<ApplicationStatusHistoryDTO> statusHistory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LoanOfferDTO getAppliedOffer() {
        return appliedOffer;
    }

    public void setAppliedOffer(LoanOfferDTO appliedOffer) {
        this.appliedOffer = appliedOffer;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public String getSesCode() {
        return sesCode;
    }

    public void setSesCode(String sesCode) {
        this.sesCode = sesCode;
    }

    public List<ApplicationStatusHistoryDTO> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<ApplicationStatusHistoryDTO> statusHistory) {
        this.statusHistory = statusHistory;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", client=" + client +
                ", credit=" + credit +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", appliedOffer=" + appliedOffer +
                ", signDate=" + signDate +
                ", sesCode='" + sesCode + '\'' +
                ", statusHistory=" + statusHistory +
                '}';
    }
}
