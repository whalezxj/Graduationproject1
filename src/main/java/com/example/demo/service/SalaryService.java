package com.example.demo.service;

import com.example.demo.mapper.SalaryMapper;
import com.example.demo.pojo.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @date 2020/10/9
 **/
@Service
public class SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    public Salary getSalary(String staffId) {
        Salary salary = salaryMapper.getSalary(staffId);
        return salary;
    }

    public List<Salary> querySalary() {
        List<Salary> list = salaryMapper.querySalary();
        return list;

    }

    public void setSalary(Salary salary) {
        salaryMapper.setSalary(salary);
    }
}
