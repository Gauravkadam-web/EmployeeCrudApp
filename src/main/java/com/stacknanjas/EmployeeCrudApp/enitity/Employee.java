package com.stacknanjas.EmployeeCrudApp.enitity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String number;

    @Column(precision = 10, scale = 2)
    private BigDecimal salary;

    // Constructors
    public Employee() {
    }

    public Employee(String name, String email, String number, BigDecimal salary) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.salary = salary;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}