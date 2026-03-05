package com.udacity.jdnd.course3.critter.user;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setSkills(dto.getSkills());
        employee.setDaysAvailable(dto.getDaysAvailable());

        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long employeeId) {
        return employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EmployeeService: Employee not found"));
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, Long employeeId) {
        Employee employee = employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "EmployeeService: Availability not found"));

        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeRequestDTO) {
        LocalDate date = employeeRequestDTO.getDate();
        Set<EmployeeSkill> skills = employeeRequestDTO.getSkills();
        List<Employee> group = employeeRepository.findAll();
        List<Employee> candidates = new ArrayList<>(); 
        for (Employee employee : group) {
            Boolean permittable = true;
            Set<DayOfWeek> availability = employee.getDaysAvailable();
            Set<EmployeeSkill> skillset = employee.getSkills(); 
            if (availability.contains(date.getDayOfWeek())) {
                for (EmployeeSkill skill : skills) {
                    if (!skillset.contains(skill)) {
                        permittable = false;
                        break;
                    }
                }

                if (permittable) {
                    candidates.add(employee);
                }
            }

        }

        return candidates;
    }
}
