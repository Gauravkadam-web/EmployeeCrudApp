package com.stacknanjas.EmployeeCrudApp.controller;

import com.stacknanjas.EmployeeCrudApp.enitity.Employee;
import com.stacknanjas.EmployeeCrudApp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // List all employees — searchable, paginated & sorted
    @GetMapping
    public String listEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(required = false) String search,
            Model model) {

        Page<Employee> employeePage = employeeService.getEmployees(page, size, sortField, sortDirection, search);

        model.addAttribute("employeePage", employeePage);
        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("totalItems", employeePage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("search", search);

        return "employee-list";
    }

    // Show form to add a new employee
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-form";
    }

    // Save new employee (from add form)
    @PostMapping
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    // Show form to edit an existing employee
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "employee-form";
    }

    // Update existing employee (from edit form)
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Integer id, @ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    // Delete employee
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}