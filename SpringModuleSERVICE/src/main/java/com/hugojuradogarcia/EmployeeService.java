package com.hugojuradogarcia;

import com.hugojuradogarcia.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EmployeeService {
    Employee findEmplyoeeByID(int id);
    int findEmployeeByFirstNameLike(String lastName);
    List<Employee> findAllEmplyoeeByID(int id);
}
