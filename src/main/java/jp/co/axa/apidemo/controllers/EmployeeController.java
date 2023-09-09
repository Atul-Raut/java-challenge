package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.common.CommonConstants;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptons.DataNotFoundException;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.utils.AppUtils;
import jp.co.axa.apidemo.validation.groups.AddEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Retrieves a list of all employees.
     *
     * @return ResponseEntity containing the list of employees or an error response.
     * @throws DataNotFoundException if no employees are found.
     */
    @GetMapping("/employees")
    public ResponseEntity<Object> getEmployees() throws DataNotFoundException {
    	logger.info("Get All employee execution started.");
        List<Employee> employees = employeeService.retrieveEmployees();
        logger.info("Get All employee execution finished. Employee Found : " + employees.size());
        //Create success response
        return AppUtils.createSuccessResponse(CommonConstants.MSG_OK, employees);
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param employeeId The ID of the employee to retrieve.
     * @return ResponseEntity containing the employee or an error response.
     * @throws DataNotFoundException if the employee with the specified ID is not found.
     */
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Object> getEmployee(@PathVariable(name="employeeId")Long employeeId) throws DataNotFoundException {
    	logger.info("Get employee execution started.");
    	Employee employee = employeeService.getEmployee(employeeId);
    	logger.info("Get employee execution finished.");
    	//Create success response
        return AppUtils.createSuccessResponse(CommonConstants.MSG_OK, employee);
    }

    /**
     * Creates a new employee.
     *
     * @param employee The employee object to be created.
     * @return ResponseEntity indicating success or an error response.
     */
    @PostMapping("/employees")
    public ResponseEntity<Object> saveEmployee(@Validated(AddEmployee.class) @RequestBody Employee employee){
    	logger.info("Create employee execution started.");
        employeeService.saveEmployee(employee);
        logger.info("Employee Saved Successfully");
        logger.info("Create employee execution finished.");
      //Create success response
        return AppUtils.createSuccessResponse(CommonConstants.MSG_OK, null);
    }

    /**
     * Deletes an employee by their ID.
     *
     * @param employeeId The ID of the employee to delete.
     * @return ResponseEntity indicating success or an error response.
     */
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(name="employeeId")Long employeeId){
    	logger.info("Delete employee execution started.");
        employeeService.deleteEmployee(employeeId);
        logger.info("Employee Deleted Successfully");
        logger.info("Delete employee execution finished.");
        return AppUtils.createSuccessResponse(CommonConstants.MSG_OK, null);
    }

    /**
     * Updates an existing employee by their ID.
     *
     * @param employee   The updated employee object.
     * @param employeeId The ID of the employee to update.
     * @return ResponseEntity indicating success or an error response.
     * @throws DataNotFoundException if the employee with the specified ID is not found.
     */
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Object> updateEmployee(@RequestBody @Valid Employee employee,
                               @PathVariable(name="employeeId")Long employeeId) throws DataNotFoundException{
    	logger.info("Update employee execution started.");
        Employee existingEmployee = employeeService.getEmployee(employeeId);
        if(existingEmployee != null){
        	logger.info("Employee details found for update.");
        	logger.debug("Preparing started for employee update.");
        	//set new name
        	if(employee.getName() != null) {
        		logger.debug("Updating employee name.");
        		existingEmployee.setName(employee.getName());
        	}
        	
        	//set new department
        	if(employee.getDepartment() != null) {
        		logger.debug("Updating department name.");
        		existingEmployee.setDepartment(employee.getDepartment());
        	}
        	
        	//set new department
        	if(employee.getSalary() != null) {
        		logger.debug("Updating salary name.");
        		existingEmployee.setSalary(employee.getSalary());
        	}
        	
        	logger.info("Updating employee details.");
        	//update into DB
            employeeService.updateEmployee(existingEmployee);
            logger.info("Employee details updated successfully..");
        }
        logger.info("Update employee execution finished.");
        return AppUtils.createSuccessResponse(CommonConstants.MSG_OK, null);
    }

}
