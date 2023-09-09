package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptons.DataNotFoundException;

import java.util.List;

public interface EmployeeService {

	/**
     * Retrieves a list of all employees.
     *
     * @return A list of Employee objects.
     * @throws DataNotFoundException If no employees are found.
     */
    List<Employee> retrieveEmployees() throws DataNotFoundException;

    /**
     * Retrieves an employee by their unique ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return The Employee object with the specified ID.
     * @throws DataNotFoundException If the employee with the given ID is not found.
     */
    Employee getEmployee(Long employeeId) throws DataNotFoundException;

    /**
     * Saves a new employee.
     *
     * @param employee The Employee object to be saved.
     */
    void saveEmployee(Employee employee);

    /**
     * Deletes an employee by their unique ID.
     *
     * @param employeeId The ID of the employee to delete.
     */
    void deleteEmployee(Long employeeId);

    /**
     * Updates the information of an existing employee.
     *
     * @param employee The Employee object with updated information.
     * @throws DataNotFoundException If the employee to be updated is not found.
     */
    void updateEmployee(Employee employee) throws DataNotFoundException;
}