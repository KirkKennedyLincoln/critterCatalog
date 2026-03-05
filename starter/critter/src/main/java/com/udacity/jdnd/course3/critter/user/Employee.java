package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

@Entity
public class Employee extends User {
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="employee_skills")
    private Set<EmployeeSkill> skills;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="employee_availability")
    private Set<DayOfWeek> daysAvailable;

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }
    public Set<EmployeeSkill> getSkills() {
        return skills;
    }
    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }
}
