package com.example.restemployee.dao;

import com.example.restemployee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer > {


    //sort by last name
    public List<Employee> findAllByOrderByFirstNameAsc();
}
