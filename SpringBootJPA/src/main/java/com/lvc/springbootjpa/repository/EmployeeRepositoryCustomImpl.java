package com.lvc.springbootjpa.repository;

import com.lvc.springbootjpa.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Date;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    EntityManager entityManager;

    @Override
    public Long getMaxEmpId() {
        try {
            String sql = "SELECT coalesce(max(e.id), 0) FROM EMPLOYEE e";
            Query query = entityManager.createQuery(sql);
            return (Long) query.getSingleResult();
        } catch (NoResultException e) {
            return 0L;
        }
    }

    @Override
    public long updateEmployee(Long empId, String fullName, Date hireDate) {
        Employee e = entityManager.find(Employee.class, empId);
        if (e == null) {
            return 0;
        }
        e.setFullName(fullName);
        e.setHireDate(hireDate);
        entityManager.flush();
        return 1;
    }
}
