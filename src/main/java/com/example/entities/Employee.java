package com.example.entities;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    private Integer employeeId;

    @Column(name = "emp_name")
    private String employeeName;

    @Column(name = "job")
    private String job;

    @Column(name = "location")
    private String location;

    @Column(name = "salary")
    private float salary;

    public Employee(){ }

    public Employee(Integer empId, String empName, String job,String location, float salary){
        this.employeeId = empId;
        this.employeeName = empName;
        this.job = job;
        this.location = location;
        this.salary = salary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}
