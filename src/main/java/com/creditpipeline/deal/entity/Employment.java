package com.creditpipeline.deal.entity;

import com.creditpipeline.deal.enums.EmploymentStatus;
import com.creditpipeline.deal.enums.Position;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="employee")
public class Employment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private EmploymentStatus employmentStatus;

    private String employerINN;

    private BigDecimal salary;

    private Position position;

    private Integer workExperienceTotal;

    private Integer workExperienceCurrent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getEmployerINN() {
        return employerINN;
    }

    public void setEmployerINN(String employerINN) {
        this.employerINN = employerINN;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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

    @Override
    public String toString() {
        return "EmploymentDTO{" +
                "id=" + id +
                ", employmentStatus=" + employmentStatus +
                ", employerINN='" + employerINN + '\'' +
                ", salary=" + salary +
                ", position=" + position +
                ", workExperienceTotal=" + workExperienceTotal +
                ", workExperienceCurrent=" + workExperienceCurrent +
                '}';
    }
}
