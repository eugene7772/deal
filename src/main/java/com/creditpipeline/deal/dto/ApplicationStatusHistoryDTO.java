package com.creditpipeline.deal.dto;

import com.creditpipeline.deal.enums.Status;

import java.time.LocalDate;

public class ApplicationStatusHistoryDTO {

    private Status status;
    private LocalDate time;
    private Status changeType;

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
