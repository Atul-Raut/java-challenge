package jp.co.axa.apidemo.employee;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployeeCreateTest {

	private HttpClient httpClient;
	String apiUrl = null;
	@BeforeEach
    public void setUp() {
		// Define the API end point URL
		apiUrl = "http://localhost:8080/api/v1/employees";
		// Initialize HttpClient and API end point URL before each test
        httpClient = HttpClients.createDefault();
	}
	
	/**
     * Test Case ID: TC001
     * Test Case Title: Verify that the employee information get stored into DB.
     * Test Objective: To ensure that employee can store successfully into DB.
     * Preconditions: The application is running.
     * Test Data: Valid employee name, salary, and department.
     */
	@Test
	public void TC1_testCreateNewEmployee() throws Exception {
		// Define the JSON payload as parameters
        String jsonPayload = "{\"name\":\"Test1\",\"salary\":1000, \"department\":\"department1\"}";
        
        // Create an HTTP POST request to the API endpoint
        HttpPost request = new HttpPost(apiUrl);
        
        // Set the request body with the JSON payload
        StringEntity requestEntity = new StringEntity(jsonPayload);
        requestEntity.setContentType("application/json");
        request.setEntity(requestEntity);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());

        System.out.println(responseBody);
        
        // Perform assertions on the response
        Assertions.assertEquals(200, response.getStatusLine().getStatusCode(), "HTTP status code should be 200");

        // the response contains the success return code
        Assertions.assertTrue(responseBody.contains("\"code\":\"0000000\""), "Response body should contain '\"code\":\"0000000\"'");
    
        // the response contains the OK message
        Assertions.assertTrue(responseBody.contains("\"message\":\"OK\""), "Response body should contain '\"message\":\"OK\"'");
	}
	
	/**
     * Test Case ID: TC002
     * Test Case Title: Verify that the employee information not passed and valid error message returned.
     * Test Objective: To ensure that valid error message.
     * Preconditions: The application is running.
     * Test Data: no employee information.
     */
	@Test
	public void TC2_testNoEmployeeDetails() throws Exception {
		// No employee information
        String jsonPayload = "";
        
        // Create an HTTP POST request to the API endpoint
        HttpPost request = new HttpPost(apiUrl);
        
        // Set the request body with the JSON payload
        StringEntity requestEntity = new StringEntity(jsonPayload);
        requestEntity.setContentType("application/json");
        request.setEntity(requestEntity);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());
        
        System.out.println(responseBody);
        System.out.println(response.getStatusLine().getStatusCode());

        // Perform assertions on the response
        Assertions.assertEquals(400, response.getStatusLine().getStatusCode(), "HTTP status code should be 400");

        // the response contains the success return code
        Assertions.assertTrue(responseBody.contains("\"code\":\"ERR0001\""), "Response body should contain '\"code\":\"ERR0001\"'");
    
        // the response message check
        Assertions.assertTrue(responseBody.contains("\"message\":\"Invalid request parameters.\""), "Response body should contain '\"message\":\"Invalid request parameters.\"'");
	}

	/**
     * Test Case ID: TC003
     * Test Case Title: No valid json for API.
     * Test Objective: To ensure that valid error message.
     * Preconditions: The application is running.
     * Test Data: invalid employee json information.
     */
	@Test
	public void TC3_testNoValidInputJson() throws Exception {
		// No employee information
        String jsonPayload = "{....";
        
        // Create an HTTP POST request to the API endpoint
        HttpPost request = new HttpPost(apiUrl);
        
        // Set the request body with the JSON payload
        StringEntity requestEntity = new StringEntity(jsonPayload);
        requestEntity.setContentType("application/json");
        request.setEntity(requestEntity);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());
        
        System.out.println(responseBody);
        System.out.println(response.getStatusLine().getStatusCode());

        // Perform assertions on the response
        Assertions.assertEquals(400, response.getStatusLine().getStatusCode(), "HTTP status code should be 400");

        // the response contains the success return code
        Assertions.assertTrue(responseBody.contains("\"code\":\"ERR0001\""), "Response body should contain '\"code\":\"ERR0001\"'");
    
        // the response message check
        Assertions.assertTrue(responseBody.contains("\"message\":\"Invalid request parameters.\""), "Response body should contain '\"message\":\"Invalid request parameters.\"'");
	}
	
	/**
     * Test Case ID: TC004
     * Test Case Title: No employee name for API.
     * Test Objective: To ensure that valid error message.
     * Preconditions: The application is running.
     * Test Data: no employee name information.
     */
	@Test
	public void TC4_testNoEmployee() throws Exception {
		// input parameters
		String jsonPayload = "{\"name\":\"\",\"salary\":1000, \"department\":\"department1\"}";
        
        // Create an HTTP POST request to the API endpoint
        HttpPost request = new HttpPost(apiUrl);
        
        // Set the request body with the JSON payload
        StringEntity requestEntity = new StringEntity(jsonPayload);
        requestEntity.setContentType("application/json");
        request.setEntity(requestEntity);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());
        
        System.out.println(responseBody);
        System.out.println(response.getStatusLine().getStatusCode());

        // Perform assertions on the response
        Assertions.assertEquals(400, response.getStatusLine().getStatusCode(), "HTTP status code should be 400");

        // the response contains the success return code
        Assertions.assertTrue(responseBody.contains("\"code\":\"ERR0001\""), "Response body should contain '\"code\":\"ERR0001\"'");
    
        // the response message check
        Assertions.assertTrue(responseBody.contains("\"message\":\"Validation failed.\""), "Response body should contain '\"message\":\"Validation failed.\"'");
        
        // the response errors check
        Assertions.assertTrue(responseBody.contains("\"errors\":[\"Employee name cannot be null.\"]"), "Response body should contain '\"errors\":[\"Employee name cannot be null.\"]'");
	}
	
	/**
     * Test Case ID: TC005
     * Test Case Title: No employee salary for API.
     * Test Objective: To ensure that valid error message.
     * Preconditions: The application is running.
     * Test Data: no employee salary information.
     */
	@Test
	public void TC5_testNoSalary() throws Exception {
		// input parameters
		String jsonPayload = "{\"name\":\"TC005\", \"department\":\"department_TC005\"}";
        
        // Create an HTTP POST request to the API endpoint
        HttpPost request = new HttpPost(apiUrl);
        
        // Set the request body with the JSON payload
        StringEntity requestEntity = new StringEntity(jsonPayload);
        requestEntity.setContentType("application/json");
        request.setEntity(requestEntity);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());
        
        System.out.println(responseBody);
        System.out.println(response.getStatusLine().getStatusCode());

        // Perform assertions on the response
        Assertions.assertEquals(400, response.getStatusLine().getStatusCode(), "HTTP status code should be 400");

        // the response contains the success return code
        Assertions.assertTrue(responseBody.contains("\"code\":\"ERR0001\""), "Response body should contain '\"code\":\"ERR0001\"'");
    
        // the response message check
        Assertions.assertTrue(responseBody.contains("\"message\":\"Validation failed.\""), "Response body should contain '\"message\":\"Validation failed.\"'");
        
        // the response errors check
        Assertions.assertTrue(responseBody.contains("\"errors\":[\"Salary must be a positive number\"]"), "Response body should contain '\"errors\":[\"Salary must be a positive number\"]'");
	}
	
	/**
     * Test Case ID: TC006
     * Test Case Title: Employee salary in - for API.
     * Test Objective: To ensure that valid error message.
     * Preconditions: The application is running.
     * Test Data: - employee salary information.
     */
	@Test
	public void TC6_testSalaryLessThan1() throws Exception {
		// input parameters
		String jsonPayload = "{\"name\":\"TC006\",\"salary\":-10, \"department\":\"department_TC005\"}";
        
        // Create an HTTP POST request to the API endpoint
        HttpPost request = new HttpPost(apiUrl);
        
        // Set the request body with the JSON payload
        StringEntity requestEntity = new StringEntity(jsonPayload);
        requestEntity.setContentType("application/json");
        request.setEntity(requestEntity);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());
        
        System.out.println(responseBody);
        System.out.println(response.getStatusLine().getStatusCode());

        // Perform assertions on the response
        Assertions.assertEquals(400, response.getStatusLine().getStatusCode(), "HTTP status code should be 400");

        // the response contains the success return code
        Assertions.assertTrue(responseBody.contains("\"code\":\"ERR0001\""), "Response body should contain '\"code\":\"ERR0001\"'");
    
        // the response message check
        Assertions.assertTrue(responseBody.contains("\"message\":\"Validation failed.\""), "Response body should contain '\"message\":\"Validation failed.\"'");
        
        // the response errors check
        Assertions.assertTrue(responseBody.contains("\"errors\":[\"Salary must be a positive number\"]"), "Response body should contain '\"errors\":[\"Salary must be a positive number\"]'");
	}
	
	/**
     * Test Case ID: TC007
     * Test Case Title: No employee department for API.
     * Test Objective: To ensure that valid error message.
     * Preconditions: The application is running.
     * Test Data: no employee department information.
     */
	@Test
	public void TC7_testNoDepartment() throws Exception {
		// input parameters
		String jsonPayload = "{\"name\":\"TC007\",\"salary\":10,\"department\":\"\"}";
        
        // Create an HTTP POST request to the API endpoint
        HttpPost request = new HttpPost(apiUrl);
        
        // Set the request body with the JSON payload
        StringEntity requestEntity = new StringEntity(jsonPayload);
        requestEntity.setContentType("application/json");
        request.setEntity(requestEntity);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());
        
        System.out.println(responseBody);
        System.out.println(response.getStatusLine().getStatusCode());

        // Perform assertions on the response
        Assertions.assertEquals(400, response.getStatusLine().getStatusCode(), "HTTP status code should be 400");

        // the response contains the success return code
        Assertions.assertTrue(responseBody.contains("\"code\":\"ERR0001\""), "Response body should contain '\"code\":\"ERR0001\"'");
    
        // the response message check
        Assertions.assertTrue(responseBody.contains("\"message\":\"Validation failed.\""), "Response body should contain '\"message\":\"Validation failed.\"'");
        
        // the response errors check
        Assertions.assertTrue(responseBody.contains("\"errors\":[\"Department cannot be null\"]"), "Response body should contain '\"errors\":[\"Department cannot be null\"]'");
	}
}
