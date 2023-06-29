package com.example.restemployee.controller;

import com.example.restemployee.entity.Employee;
import com.example.restemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String findAll(Model model, SecurityContextHolder contextHolder){
        List<Employee> employees = employeeService.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("employees",employees);
        model.addAttribute("authentication",authentication);
        return "list-employees";
    }


    @GetMapping("/showAddForm")
    public String showAddForm(Model model){

        Employee employee = new Employee();
        model.addAttribute("employee",employee);

        return "employee-form";


    }
    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("empId") int id,Model model){

        Employee employee = employeeService.findById(id);
        model.addAttribute("employee",employee);

        return "employee-form";
    }

    @PostMapping("/save")
    public String addEmployee(@ModelAttribute("employee") Employee employee){

        employeeService.save(employee);

        return "redirect:/api/employees" ;
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("empId") int id){
        employeeService.deleteById(id);
        return "redirect:/api/employees" ;
    }

//    @GetMapping("/employees/{id}")
//    public Employee getEmployee(@PathVariable int id){
//        Employee newEmployee = employeeService.findById(id);
//        if(newEmployee==null)
//            throw new RuntimeException("Not found !!!"+ id);
//        return newEmployee;
//    }

//    @PostMapping("/employees")
//    public Employee addEmployee(@RequestBody Employee employee){
//
//        employee.setId(0);
//        Employee newEmployee = employeeService.save(employee);
//
//        return newEmployee;
//    }

//    @PutMapping("/employees")
//    public Employee updateEmployee(@RequestBody Employee employee){
//        Employee newEmployee = employeeService.save(employee);
//        return newEmployee;
//    }

//    @DeleteMapping("/employees/{id}")
//    public String deleteEmployee(@PathVariable int id){
//        Employee employee  = employeeService.findById(id);
//
//        if(employee == null){
//            throw new RuntimeException("Employee doesnt exist !!"+id);
//        }
//        employeeService.deleteById(id);
//        return "delete "+id+" done ";
//    }
}
