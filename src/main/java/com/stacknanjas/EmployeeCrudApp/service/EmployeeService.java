package com.stacknanjas.EmployeeCrudApp.service;

import com.stacknanjas.EmployeeCrudApp.enitity.Employee;
import com.stacknanjas.EmployeeCrudApp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create / Save employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get all employees (kept for reference)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get paginated + sorted + searched employees
    public Page<Employee> getEmployees(int page, int size, String sortField, String sortDirection, String search) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        if (StringUtils.hasText(search)) {
            return employeeRepository
                    .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrNumberContainingIgnoreCase(
                            search, search, search, pageable);
        }

        return employeeRepository.findAll(pageable);
    }

    // Get employee by id
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    // Update employee
    public Employee updateEmployee(Integer id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setNumber(employeeDetails.getNumber());
        employee.setSalary(employeeDetails.getSalary());

        return employeeRepository.save(employee);
    }

    // Delete employee
    public void deleteEmployee(Integer id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }
}