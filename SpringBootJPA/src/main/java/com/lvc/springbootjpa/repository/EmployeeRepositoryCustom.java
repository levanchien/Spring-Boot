package com.lvc.springbootjpa.repository;

import java.util.Date;

public interface EmployeeRepositoryCustom {
    Long getMaxEmpId();
    long updateEmployee(Long empId, String fullName, Date hireDate);
}
