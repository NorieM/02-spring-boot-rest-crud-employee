package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // set up construction injection
    @Autowired
    public EmployeeDAOJPAImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        // run query
        List<Employee> employees = theQuery.getResultList();

        // return results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        // find employee based on ID
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // return employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        // save/update employee
        Employee dbEmployee = entityManager.merge(theEmployee);

        // return employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {

        // find employee by ID
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // delete employee 
        entityManager.remove(theEmployee);

    }

}
