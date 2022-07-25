package com.creditpipeline.deal.entity;

import com.creditpipeline.deal.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="history")
public class ApplicationStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Status status;
    private LocalDate time;
    private Status changeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Status getChangeType() {
        return changeType;
    }

    public void setChangeType(Status changeType) {
        this.changeType = changeType;
    }

    @Override
    public String toString() {
        return "ApplicationStatusHistoryDTO{" +
                "id=" + id +
                ", status=" + status +
                ", time=" + time +
                ", changeType=" + changeType +
                '}';
    }
}

