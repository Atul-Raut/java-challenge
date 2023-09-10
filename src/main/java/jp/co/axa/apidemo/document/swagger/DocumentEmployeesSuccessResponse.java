package jp.co.axa.apidemo.document.swagger;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jp.co.axa.apidemo.entities.Employee;

@ApiModel(description = "Api response")
public class DocumentEmployeesSuccessResponse{

	@ApiModelProperty(value = "Response code indicating success or a specific status.")
	private String code;       // Response code indicating success or a specific status.
	@ApiModelProperty(value = "A message describing the success or result of the operation.")
    private String message;    // A message describing the success or result of the operation.
	@ApiModelProperty(value = "The actual response data.")
    private List<Employee> response;   // The actual response data.

    /**
     * Constructs a new SuccessResponse with the provided code, message, and response data.
     *
     * @param code    The response code.
     * @param message A message describing the success or result.
     * @param response The actual response data.
     */
    public DocumentEmployeesSuccessResponse(String code, String message, List<Employee> response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    /**
     * Gets the response code.
     *
     * @return The response code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the response code.
     *
     * @param code The response code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the success message or result description.
     *
     * @return The success message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the success message or result description.
     *
     * @param message The success message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the actual response data.
     *
     * @return The response data.
     */
    public List<Employee> getResponse() {
        return response;
    }

    /**
     * Sets the actual response data.
     *
     * @param response The response data to set.
     */
    public void setResponse(List<Employee> response) {
        this.response = response;
    }

}