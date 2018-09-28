package com.hugojuradogarcia;

import com.hugojuradogarcia.dao.CustomEmployeeRepository;
import com.hugojuradogarcia.dao.EmployeeRepository;
import com.hugojuradogarcia.entity.Employee;
import com.hugojuradogarcia.exception.GenericNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomEmployeeRepository customEmployeeRepository;

    @Override
    public Employee findEmplyoeeByID(int id) {

        Employee employee = employeeRepository.findOne(id);

        if (employee != null){
            return employee;
        }
        throw new GenericNotFoundException();
    }

    @Override
    public int findEmployeeByFirstNameLike(String lastName) {
        int number = employeeRepository.findEmployeeByFirstNameLike(lastName);

        if (number > 0){
            return number;
        }
        throw  new GenericNotFoundException();

    }

    @Override
    public List<Employee> findAllEmplyoeeByID(int id) {
        return customEmployeeRepository.findEmployeeById(id);
    }
}
