package com.hugojuradogarcia.dao;

import com.hugojuradogarcia.entity.Employee;

import java.util.List;

public interface CustomEmployeeRepository {
    List<Employee> findEmployeeById(int id);
}
