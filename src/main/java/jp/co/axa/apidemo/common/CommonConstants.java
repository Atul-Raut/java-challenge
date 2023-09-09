package jp.co.axa.apidemo.common;

/**
 * 
 * @author rautatul
 *
 */
public class CommonConstants {

	//Represents an error code related to validation errors, possibly for input validation.
	public static final String CODE_ERR_VALIDATION = "ERR0001";
	//Represents an error code for situations where data is not found, such as when querying a database or a resource.
	public static final String CODE_ERR_DATA_NOT_FOUND = "ERR0002";
	//Represents a generic error code for unexpected or unhandled errors that don't fall into the above categories.
	public static final String CODE_ERR_UNEXPECTED = "ERR9999";
	
	//Represents a success code, often used to indicate successful operations.
	public static final String CODE_SUCCESS = "0000000";
	
	//Represents an error message for validation failures. It's typically used when data validation fails.
	public static final String MSG_ERR_VALIDATION = "Validation failed.";
	//Represents an error message for cases where invalid parameters are provided in a request.
	public static final String MSG_ERR_INVALID_PARAMETER = "Invalid request parameters.";
	//Represents an error message when data is not found, providing a description of the error.
	public static final String MSG_ERR_DATA_NOT_FOUND  = "Data not found.";
	//Represents a generic error message for unexpected or unhandled errors that don't fall into specific categories.
	public static final String MSG_ERR_UNEXPECTED = "An unexpected error occurred.";
	//Represents a success message, often used to indicate successful operations.
	public static final String MSG_OK = "OK";
}
