package com.udacity.jdnd.course3.critter.schedule;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Schedule createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setEmployeeIds(scheduleDTO.getEmployeeIds());
        schedule.setId(scheduleDTO.getId());
        schedule.setPetIds(scheduleDTO.getPetIds());
        schedule.setScheduledAcitivies(scheduleDTO.getActivities());
        schedule.setScheduledTime(scheduleDTO.getDate());

        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId) {
        Pet pet = petRepository
            .findById(petId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet not found"));
        Long pid = pet.getId();
        List<Schedule> schedules = scheduleRepository.findAll();
        List<Schedule> appointments = new ArrayList<>(); 
        for (Schedule schedule : schedules) {
            List<Long> petIds = schedule.getPetIds();
            for (Long id : petIds) {
                if (id.equals(pid)) {
                    appointments.add(schedule);
                }
            }
        }

        return appointments;
    }

    public List<Schedule> getScheduleForEmployee(Long employeeId) {
        Employee employee = employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        Long eid = employee.getId();
        List<Schedule> schedules = scheduleRepository.findAll();
        List<Schedule> shifts = new ArrayList<>(); 
        for (Schedule schedule : schedules) {
            List<Long> employeeIds = schedule.getEmployeeIds();
            for (Long id : employeeIds) {
                if (id.equals(eid)) {
                    shifts.add(schedule);
                }
            }
        }

        return shifts;
    }

    public List<Schedule> getScheduleForCustomer(Long customerId) {
        Customer customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        List<Long> petIds = customer.getPetIds();
        List<Schedule> appointments = new ArrayList<>(); 
        for (Long id : petIds) {
            appointments.addAll(getScheduleForPet(id));
        }

        return appointments;
    }
}
