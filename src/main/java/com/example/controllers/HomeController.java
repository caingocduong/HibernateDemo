package com.example.controllers;

import com.example.dao.EmployeeDAO;
import com.example.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@ComponentScan("com.example")
public class HomeController {

    private EmployeeDAO empDAO;

    @Autowired
    public HomeController(EmployeeDAO empDAO){
        this.empDAO = empDAO;
    }

    @ModelAttribute("allEmployees")
    public List<Employee> retrieveAllEmployees(){

        return empDAO.retrieveEmployees();
    }

    @GetMapping({"/","/home"})
    public String home(Model model){
        model.addAttribute("employee",new Employee());

        return "home";
    }

    @RequestMapping(value="/home", params = {"delete"})
    public String deleteEmployee(HttpServletRequest request){
        Integer empId = Integer.valueOf(request.getParameter("delete"));
        empDAO.delete(empId);

        return "redirect:/home";
    }

    @RequestMapping(value="/home", params = {"edit"})
    public String editEmployee(Model model, HttpServletRequest request){
        Integer empId = Integer.valueOf(request.getParameter("edit"));
        Employee empTemp = empDAO.getEmployeeById(empId);
        model.addAttribute("employee", empTemp);

        return "home";
    }

    @RequestMapping(value="/home", params = {"save"})
    public String saveEditedEmployee(@ModelAttribute(value="employee") Employee employee){

        empDAO.update(employee);

        return "redirect:/home";
    }

    @GetMapping("/create-employee")
    public String newEmployeeGet(Model model){
        model.addAttribute("employee", new Employee());

        return "createEmployee";
    }

    @PostMapping("/create-employee")
    public String newEmployeePost(@ModelAttribute(value="employee") Employee employee){

        Employee newEmployee = new Employee();

        newEmployee.setEmployeeId(employee.getEmployeeId());
        newEmployee.setEmployeeName(employee.getEmployeeName());
        newEmployee.setJob(employee.getJob());
        newEmployee.setSalary(employee.getSalary());
        newEmployee.setLocation(employee.getLocation());

        empDAO.save(newEmployee);

        return "redirect:/home";
    }


}
