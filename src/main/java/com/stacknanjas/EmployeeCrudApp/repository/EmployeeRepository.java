package com.stacknanjas.EmployeeCrudApp.repository;


import com.stacknanjas.EmployeeCrudApp.enitity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrNumberContainingIgnoreCase(
            String name, String email, String number, Pageable pageable);

}