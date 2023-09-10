package jp.co.axa.apidemo.utils;

import java.util.Objects;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jp.co.axa.apidemo.common.CommonConstants;
import jp.co.axa.apidemo.responses.SuccessResponse;

public class AppUtils {
	
	// ObjectMapper for JSON serialization
	private static ObjectMapper mapper = null;
	// Logger for logging
	private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);
	
	// Generate a Version 4 (random) UUID
	private static final UUID uuid = UUID.randomUUID();

	// Initialize the ObjectMapper to handle JSON serialization
	static {
		if (mapper == null) {
			mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.INDENT_OUTPUT);
		}
	}
	
	/**
	 * Convert an object to a JSON string.
	 *
	 * @param object The object to be converted.
	 * @return JSON string representation of the object.
	 * @throws Exception If an error occurs during JSON serialization.
	 */
	public static String getJsonStringFromObject(Object object) throws Exception {
		try {
			return mapper.writeValueAsString(object);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Create an error response as a ResponseEntity.
	 *
	 * @param response The error response object.
	 * @param status   The HTTP status code for the response.
	 * @return ResponseEntity containing the error response.
	 */
	public static ResponseEntity<Object> createErrorResponse(Object response, HttpStatus status) {
		if (status == null) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		String output = Objects.toString(response, "");
		try {
			// Convert the error response object to a JSON string
			output = getJsonStringFromObject(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.error("Sending Error Response : " + output);
		return new ResponseEntity<Object>(response, status);
	}
	
	/**
	 * Create a success response as a ResponseEntity.
	 *
	 * @param message  The success message.
	 * @param response The response data.
	 * @return ResponseEntity containing the success response.
	 */
	public static ResponseEntity<Object> createSuccessResponse(String message, Object response) {
		String output = null;
		// Create a success response object
		SuccessResponse successResponse = new SuccessResponse(CommonConstants.CODE_SUCCESS,
				Objects.toString(message, CommonConstants.MSG_OK), response);
		 
		try {
			// Convert the success response object to a JSON string
			output = getJsonStringFromObject(successResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.error("Sending Success Response : " + output);
		return new ResponseEntity<Object>(successResponse, HttpStatus.OK);
	}
	
	/**
	 * Get a UUID (Universally Unique Identifier).
	 *
	 * @return A UUID as a string.
	 */
	public static String getUUID() {
		// Convert UUID to a string representation
		return uuid.toString();
	}
}
