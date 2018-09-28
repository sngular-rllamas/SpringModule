package com.hugojuradogarcia.dao;

import com.hugojuradogarcia.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(value = "SELECT COUNT(*) " +
            "FROM employee " +
            "WHERE first_name LIKE 'H%' " +
            "AND last_name = :lastName", nativeQuery = true)
    int findEmployeeByFirstNameLike(@Param("lastName") String lastName);
}
