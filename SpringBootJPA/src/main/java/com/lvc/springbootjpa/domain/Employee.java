package com.lvc.springbootjpa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    private Long id;

    @Column(name = "Emp_No", length = 30, nullable = false)
    private String empNo;

    @Column(name = "Full_Name", length = 128, nullable = false)
    private String fullName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Hire_Date", nullable = false)
    private Date hireDate;

    @Override
    public String toString() {
        return this.getEmpNo() + ", " + this.getFullName();
    }

}
