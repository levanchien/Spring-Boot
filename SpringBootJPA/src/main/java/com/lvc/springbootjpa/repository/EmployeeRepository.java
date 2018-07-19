package com.lvc.springbootjpa.repository;

import com.lvc.springbootjpa.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByEmpNo(String empNo);
    List<Employee> findByFullNameLike(String fullName);
    List<Employee> findByHireDateGreaterThan(Date hireDate);
    @Query("SELECT coalesce(max(e.id), 0) FROM Employee e")
    Optional<Long> getMaxId();
}
