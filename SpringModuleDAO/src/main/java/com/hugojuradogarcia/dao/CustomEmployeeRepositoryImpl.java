package com.hugojuradogarcia.dao;

import com.hugojuradogarcia.entity.Employee;
import com.hugojuradogarcia.exception.GenericNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomEmployeeRepositoryImpl implements CustomEmployeeRepository {

    private EntityManager entityManager;

    @PersistenceContext(unitName = "entityManagerFactoryBean")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    @Override
    public List<Employee> findEmployeeById(int id) {

        String sql = "SELECT * FROM employee WHERE id = ?1";
        Query query = getEntityManager().createNativeQuery(sql);
        query.setParameter(1, id);
        List<Employee> employees = new ArrayList<>();

        List<Object[]> objects = query.getResultList();

        if (!objects.isEmpty()){

            for (Object[] employee:
                    objects) {
                Employee employeeBuilder = Employee.builder()
                        .id((Integer) employee[0])
                        .firstName((String) employee[1])
                        .lastName((String) employee[2])
                        .build();

                employees.add(employeeBuilder);
            }
            return employees;
        }
        throw new GenericNotFoundException();
    }
}
