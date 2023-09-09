package jp.co.axa.apidemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import jp.co.axa.apidemo.validation.groups.AddEmployee;
import jp.co.axa.apidemo.validator.AtLeastOneNotNull;

@AtLeastOneNotNull
@Entity
@Table(name="EMPLOYEE")
public class Employee {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    // Employee's name
    @NotBlank(groups = {AddEmployee.class}, message = "Employee name cannot be null.")
    @Column(name="EMPLOYEE_NAME")
    private String name;

    // Employee's salary
    @Positive(groups = {AddEmployee.class}, message = "Salary must be a positive number")
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    // Employee's department
    @NotBlank(groups = {AddEmployee.class}, message = "Department cannot be null")
    @Column(name="DEPARTMENT")
    private String department;

    // Getter and Setter methods for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for salary
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    // Getter and Setter methods for department
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
