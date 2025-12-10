package com.employee.dimata.controller;

import com.employee.dimata.entity.Employee;
import com.employee.dimata.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.delete(id);
        return "Deleted";
    }
}
