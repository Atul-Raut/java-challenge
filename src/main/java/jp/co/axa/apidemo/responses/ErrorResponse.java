package jp.co.axa.apidemo.responses;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Api error response")
public class ErrorResponse{

	@ApiModelProperty(value = "Error code indicating the specific error.")
	private String code;       // Error code indicating the specific error.
	@ApiModelProperty(value = "A message describing the error.")
    private String message;    // A message describing the error.
	@ApiModelProperty(value = "A list of error details or messages.")
    private List<String> errors; // A list of error details or messages.

    /**
     * Constructs a new ErrorResponse with the provided code, message, and error details.
     *
     * @param code    The error code.
     * @param message A message describing the error.
     * @param errors  A list of error details or messages.
     */
    public ErrorResponse(String code, String message, List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    /**
     * Gets the error code.
     *
     * @return The error code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the error code.
     *
     * @param code The error code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the error message.
     *
     * @return The error message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     *
     * @param message The error message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the list of error details.
     *
     * @return The list of error details.
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Sets the list of error details.
     *
     * @param errors The list of error details to set.
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}