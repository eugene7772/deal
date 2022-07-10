package com.creditpipeline.deal.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id_client")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_credit")
    private Credit credit;
    private String status;
    private LocalDate creationDate;
    private Boolean appliedOffer;
    private LocalDate signDate;
    private String sesCode;
    private String statusHistory;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getAppliedOffer() {
        return appliedOffer;
    }

    public void setAppliedOffer(Boolean appliedOffer) {
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

    public String getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(String statusHistory) {
        this.statusHistory = statusHistory;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", client=" + client +
                ", credit='" + credit + '\'' +
                ", status='" + status + '\'' +
                ", creationDate=" + creationDate +
                ", appliedOffer=" + appliedOffer +
                ", signDate=" + signDate +
                ", sesCode='" + sesCode + '\'' +
                ", statusHistory='" + statusHistory + '\'' +
                '}';
    }
}
