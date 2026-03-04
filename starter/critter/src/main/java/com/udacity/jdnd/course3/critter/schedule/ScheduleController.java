package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleService.createSchedule(scheduleDTO);

        ScheduleDTO dto = new ScheduleDTO();
        dto.setEmployeeIds(new ArrayList<>(schedule.getEmployeeIds()));
        dto.setId(schedule.getId());
        dto.setPetIds(new ArrayList<>(schedule.getPetIds()));
        dto.setActivities(schedule.getScheduledAcitivies());
        dto.setDate(schedule.getScheduledTime());

        return dto;
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> dates = scheduleService.getAllSchedules();
        List<ScheduleDTO> schedules = new ArrayList<>();
        for (Schedule schedule : dates) {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setEmployeeIds(new ArrayList<>(schedule.getEmployeeIds()));
            dto.setId(schedule.getId());
            dto.setPetIds(new ArrayList<>(schedule.getPetIds()));
            dto.setActivities(schedule.getScheduledAcitivies());
            dto.setDate(schedule.getScheduledTime());

            schedules.add(dto);
        }

        return schedules;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.getScheduleForPet(petId);
        List<ScheduleDTO> appointments = new ArrayList<>(); 
        for (Schedule schedule : schedules) {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setId(schedule.getId()); 
            dto.setActivities(schedule.getScheduledAcitivies());
            dto.setDate(schedule.getScheduledTime());
            dto.setEmployeeIds(new ArrayList<>(schedule.getEmployeeIds()));
            dto.setPetIds(new ArrayList<>(schedule.getPetIds()));

            appointments.add(dto);
        }

        return appointments;
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.getScheduleForEmployee(employeeId);
        List<ScheduleDTO> shifts = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setId(schedule.getId());
            dto.setActivities(schedule.getScheduledAcitivies());
            dto.setDate(schedule.getScheduledTime());
            dto.setEmployeeIds(new ArrayList<>(schedule.getEmployeeIds()));
            dto.setPetIds(new ArrayList<>(schedule.getPetIds()));

            shifts.add(dto);
        }

        return shifts;
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.getScheduleForCustomer(customerId);
        List<ScheduleDTO> appointments = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleDTO dto = new ScheduleDTO();
            dto.setId(schedule.getId());
            dto.setActivities(schedule.getScheduledAcitivies());
            dto.setDate(schedule.getScheduledTime());
            dto.setEmployeeIds(new ArrayList<>(schedule.getEmployeeIds()));
            dto.setPetIds(new ArrayList<>(schedule.getPetIds()));
            
            appointments.add(dto);
        }

        return appointments;
    }
}
