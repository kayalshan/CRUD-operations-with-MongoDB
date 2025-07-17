package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAll() {
        return repository.findAll();
    }

    public Employee getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
    }

    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    public Employee update(String id, Employee employee) {
        Employee existing = getById(id);
        existing.setName(employee.getName());
        existing.setRole(employee.getRole());
        existing.setEmail(employee.getEmail());
        return repository.save(existing);
    }

    public void delete(String id) {
        Employee existing = getById(id);
        repository.delete(existing);
    }
}
