package jp.co.axa.apidemo.responses;

public class SuccessResponse{

	private String code;       // Response code indicating success or a specific status.
    private String message;    // A message describing the success or result of the operation.
    private Object response;   // The actual response data.

    /**
     * Constructs a new SuccessResponse with the provided code, message, and response data.
     *
     * @param code    The response code.
     * @param message A message describing the success or result.
     * @param response The actual response data.
     */
    public SuccessResponse(String code, String message, Object response) {
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
    public Object getResponse() {
        return response;
    }

    /**
     * Sets the actual response data.
     *
     * @param response The response data to set.
     */
    public void setResponse(Object response) {
        this.response = response;
    }

}