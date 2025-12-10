package com.employee.dimata.service;

import com.employee.dimata.entity.Employee;
import com.employee.dimata.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Argon2PasswordEncoder passwordEncoder;

    public Employee create(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee update(Long id, Employee newData) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setName(newData.getName());
                    emp.setEmail(newData.getEmail());

                    if (newData.getPassword() != null && !newData.getPassword().isBlank()) {
                        emp.setPassword(passwordEncoder.encode(newData.getPassword()));
                    }

                    return employeeRepository.save(emp);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }
}
