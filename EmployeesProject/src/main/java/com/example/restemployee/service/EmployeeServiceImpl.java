package com.example.restemployee.service;

//import com.example.restemployee.dao.EmployeeDAO;
import com.example.restemployee.dao.EmployeeRepository;
import com.example.restemployee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> res = employeeRepository.findById(id);
        Employee employee = null;
        if(res.isPresent()){
            employee=res.get();
        }
        else{
            throw new RuntimeException("dind found id bla "+ id);
        }
        return employee;
    }

    @Override
//    @Transactional
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
//    @Transactional
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
