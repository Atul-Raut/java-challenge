package jp.co.axa.apidemo.employee;

import java.util.stream.IntStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetAllEmployeeTest {

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
     * Test Case Title: Verify that the no employees.
     * Test Objective: To ensure that no employees returned.
     * Preconditions: The application is running. Assumed no employee registered.
     */
	@Test
	public void TC1_testNoEmployee() throws Exception {

        // Create an HTTP GET request to the API endpoint
        HttpGet request = new HttpGet(apiUrl);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());

        System.out.println(responseBody);
        System.out.println(response.getStatusLine().getStatusCode());
        
        // Perform assertions on the response
        Assertions.assertEquals(404, response.getStatusLine().getStatusCode(), "HTTP status code should be 404");

        // check returen code
        Assertions.assertTrue(responseBody.contains("\"code\":\"ERR0002\""), "Response body should contain '\"code\":\"ERR0002\"'");
    
        // check message
        Assertions.assertTrue(responseBody.contains("\"message\":\"Data not found.\""), "Response body should contain '\"message\":\"Data not found.\"'");
        
     // check errors
        Assertions.assertTrue(responseBody.contains("\"errors\":null"), "Response body should contain '\"errors\":null'");
	}
	
	
	/**
     * Test Case ID: TC002
     * Test Case Title: Verify that the all employees returned.
     * Test Objective: To ensure that all employees returned.
     * Preconditions: The application is running. Assumed no employee registered.
     */
	@Test
	public void TC2_testAllEmployee() throws Exception {
		int noOfEmp = 5;
		createEmployee(noOfEmp);

        // Create an HTTP GET request to the API endpoint
        HttpGet request = new HttpGet(apiUrl);

        // Execute the request and receive the response
        HttpResponse response = httpClient.execute(request);

        // Extract the response body as a string
        String responseBody = EntityUtils.toString(response.getEntity());

        System.out.println(responseBody);
        System.out.println(response.getStatusLine().getStatusCode());
        
        // Perform assertions on the response
        Assertions.assertEquals(200, response.getStatusLine().getStatusCode(), "HTTP status code should be 200");

        // check returen code
        Assertions.assertTrue(responseBody.contains("\"code\":\"0000000\""), "Response body should contain '\"code\":\"0000000\"'");
       
        // check message
        Assertions.assertTrue(responseBody.contains("\"message\":\"OK\""), "Response body should contain '\"message\":\"OK\"'");
    
        int count = 0;
        int index = responseBody.indexOf("Test1_");

        while (index != -1) {
            count++;
            index = responseBody.indexOf("Test1_", index + 1);
        }
        
        // check errors
        Assertions.assertTrue((noOfEmp == count), "All data not returned.");
	}


	/**
	 * createEmployee
	 * @param noOfEmp
	 * @throws Exception
	 */
	private void createEmployee(int noOfEmp) throws Exception {
		
		IntStream.range(0, noOfEmp).forEach(i -> {
			try {
				// Define the JSON payload as parameters
		        String jsonPayload = "{\"name\":\"Test1_" + i + "\",\"salary\":1000, \"department\":\"department\"}";
		        
		        // Create an HTTP POST request to the API endpoint
		        HttpPost request = new HttpPost(apiUrl);
		        
		        // Set the request body with the JSON payload
		        StringEntity requestEntity;
				
					requestEntity = new StringEntity(jsonPayload);
				
		        requestEntity.setContentType("application/json");
		        request.setEntity(requestEntity);
		
		        // Execute the request and receive the response
		        HttpResponse response = httpClient.execute(request);
		
		        // Extract the response body as a string
		        String responseBody = EntityUtils.toString(response.getEntity());
		
		        System.out.println(responseBody);
		        
		        // Perform assertions on the response
		        Assertions.assertEquals(200, response.getStatusLine().getStatusCode(), "HTTP status code should be 200");
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}
