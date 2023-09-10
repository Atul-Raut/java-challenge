package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.common.CommonConstants;
import jp.co.axa.apidemo.document.swagger.DocumentEmployeeSuccessResponse;
import jp.co.axa.apidemo.document.swagger.DocumentEmployeesSuccessResponse;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptons.DataNotFoundException;
import jp.co.axa.apidemo.responses.ErrorResponse;
import jp.co.axa.apidemo.responses.SuccessResponse;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.utils.AppUtils;
import jp.co.axa.apidemo.validation.groups.AddEmployee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Api(tags = "Employee")
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
    @ApiOperation(value = "Get all employees", notes = "Retrieve a list of all employees.")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "List of employees retrieved successfully",
    				response=DocumentEmployeesSuccessResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
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
    @ApiOperation(value = "Get an employee by ID", 
    		notes = "Retrieve an employee's details by providing their ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee retrieve successfully"
            		, response = DocumentEmployeeSuccessResponse.class),
            @ApiResponse(code = 400, message = "Invalid input data", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Object> getEmployee(
    		//@ApiParam(value = "The unique ID of the employee", required = true)
    		@PathVariable(name="employeeId")Long employeeId) throws DataNotFoundException {
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
    @ApiOperation(value = "Save a new employee", notes = "Create a new employee record.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee information created successfully",
            		response = SuccessResponse.class),
            @ApiResponse(code = 400, message = "Invalid input data", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    @PostMapping("/employees")
    public ResponseEntity<Object> saveEmployee(
    		@ApiParam(value = "The employee data to be saved", required = true)
    		@Validated(AddEmployee.class) @RequestBody Employee employee){
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee information deleted successfully", 
            		response = SuccessResponse.class),
            @ApiResponse(code = 400, message = "Invalid input data", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    @ApiOperation(value = "Delete an employee by ID", notes = "Remove an employee from the system by providing their ID.")
    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(
    		//@ApiParam(value = "The unique ID of the employee")
    		@PathVariable(name="employeeId") Long employeeId){
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee information updated successfully", response = SuccessResponse.class),
            @ApiResponse(code = 400, message = "Invalid input data", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponse.class)
    })
    @ApiOperation(value = "Update employee details", notes = "Update an employee's details with new information.")
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Object> updateEmployee(
    		@ApiParam(value = "The employee data to be update", required = true)
    		@RequestBody @Valid Employee employee,
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
