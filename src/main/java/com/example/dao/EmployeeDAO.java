package com.example.dao;

import com.example.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> retrieveEmployees();
    public Employee getEmployeeById(int id);
    public void save(Employee emp);
    public void update(Employee emp);
    public void delete(int id);
}
