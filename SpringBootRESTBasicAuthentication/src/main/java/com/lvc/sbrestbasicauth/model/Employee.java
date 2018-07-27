package com.lvc.sbrestbasicauth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private String empNo;
    private String empName;
    private String position;
}
