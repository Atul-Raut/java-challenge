package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.common.CommonConstants;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptons.DataNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * Retrieves a list of all employees.
	 *
	 * @return A list of Employee objects.
	 * @throws DataNotFoundException If no employees are found.
	 */
	public List<Employee> retrieveEmployees() throws DataNotFoundException {
		logger.debug("Retrieve Employees execution started.");
		List<Employee> employees = employeeRepository.findAll();
		if (null == employees || employees.isEmpty()) {
			throw new DataNotFoundException(CommonConstants.CODE_ERR_DATA_NOT_FOUND, CommonConstants.MSG_ERR_DATA_NOT_FOUND);
		}
		return employees;
	}
	
	/**
	 * Retrieves an employee by their unique ID.
	 *
	 * @param employeeId The ID of the employee to retrieve.
	 * @return The Employee object with the specified ID.
	 * @throws DataNotFoundException If the employee with the given ID is not found.
	 */
	@Cacheable(value = "employees", key = "#employeeId")
	public Employee getEmployee(Long employeeId) throws DataNotFoundException {
		Optional<Employee> optEmp = employeeRepository.findById(employeeId);
		if (!optEmp.isPresent()) {
			throw new DataNotFoundException(CommonConstants.CODE_ERR_DATA_NOT_FOUND, CommonConstants.MSG_ERR_DATA_NOT_FOUND);
		}
		return optEmp.get();
	}
	
	/**
	 * Saves a new employee.
	 *
	 * @param employee The Employee object to be saved.
	 */
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	/**
	 * Deletes an employee by their unique ID.
	 *
	 * @param employeeId The ID of the employee to delete.
	 */
	@CacheEvict(value = "employees", key = "#employeeId")
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}
	
	/**
	 * Updates the information of an existing employee.
	 *
	 * @param employee The Employee object with updated information.
	 */
	@CacheEvict(value = "employees", key = "#employeeId")
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}