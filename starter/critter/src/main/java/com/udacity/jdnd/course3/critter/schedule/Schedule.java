package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER) // Claude code recommended using EAGER
    @Column(name="schedule_petids")
    private List<Long> petIds;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="schedule_employeeids")
    
    private List<Long> employeeIds;

    @Column(name="schedule_time")
    private LocalDate scheduledTime;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="schedule_activities")
    private Set<EmployeeSkill> scheduledAcitivies;

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }
    public Long getId() {
        return id;
    }
    public List<Long> getPetIds() {
        return petIds;
    }
    public Set<EmployeeSkill> getScheduledAcitivies() {
        return scheduledAcitivies;
    }
    public LocalDate getScheduledTime() {
        return scheduledTime;
    }
    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setPetIds(List<Long> petIds) {
        this.petIds = petIds;
    }
    public void setScheduledAcitivies(Set<EmployeeSkill> scheduledAcitivies) {
        this.scheduledAcitivies = scheduledAcitivies;
    }
    public void setScheduledTime(LocalDate scheduledTime) {
        this.scheduledTime = scheduledTime;
    }
}
