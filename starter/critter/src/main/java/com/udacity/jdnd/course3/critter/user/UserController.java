package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer saved = customerService.saveCustomer(customerDTO);

        CustomerDTO result = new CustomerDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setPhoneNumber(saved.getPhoneNumber());
        result.setPetIds(saved.getPetIds() != null ? new ArrayList<>(saved.getPetIds()) : null);
        result.setNotes(saved.getNotes());

        return result;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> group = customerService.getAllCustomers();
        List<CustomerDTO> dtoList = new ArrayList<>();
        for (Customer customer : group) {
            CustomerDTO dto = new CustomerDTO();
            dto.setId(customer.getId());
            dto.setName(customer.getName());
            dto.setNotes(customer.getNotes());
            dto.setPetIds(customer.getPetIds() != null ? new ArrayList<>(customer.getPetIds()) : null);
            dto.setPhoneNumber(customer.getPhoneNumber());

            dtoList.add(dto);
        }

        return dtoList;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = customerService.getOwnerByPet(petId);
        CustomerDTO dto = new CustomerDTO();

        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setNotes(customer.getNotes());
        dto.setPetIds(customer.getPetIds() != null ? new ArrayList<>(customer.getPetIds()) : null);
        dto.setPhoneNumber(customer.getPhoneNumber());

        return dto;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee saved = employeeService.saveEmployee(employeeDTO);

        EmployeeDTO result = new EmployeeDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        result.setSkills(saved.getSkills());
        result.setDaysAvailable(saved.getDaysAvailable());

        return result;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setDaysAvailable(employee.getDaysAvailable());
        dto.setSkills(employee.getSkills());

        return dto;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
        List<Employee> available = employeeService.findEmployeesForService(employeeRequestDTO);
        List<EmployeeDTO> candidates = new ArrayList<>(); 
        for (Employee employee : available) {
            EmployeeDTO dto = new EmployeeDTO();
            dto.setDaysAvailable(employee.getDaysAvailable());
            dto.setId(employee.getId());
            dto.setName(employee.getName());
            dto.setSkills(employee.getSkills());

            candidates.add(dto);
        }

        return candidates;
    }
}
