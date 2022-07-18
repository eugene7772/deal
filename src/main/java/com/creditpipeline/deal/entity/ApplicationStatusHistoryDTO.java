package com.creditpipeline.deal.entity;

import com.creditpipeline.deal.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="history")
public class ApplicationStatusHistoryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "id_history")
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
                "status=" + status +
                ", time=" + time +
                ", changeType=" + changeType +
                '}';
    }
}
