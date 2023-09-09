package jp.co.axa.apidemo.exceptons;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorDescription;

    /**
     * Constructs a new BusinessException with only an error code.
     *
     * @param errorCode The error code associated with the exception.
     */
    public BusinessException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new BusinessException with an error code and an inner exception.
     *
     * @param errorCode      The error code associated with the exception.
     * @param innerException The inner exception that caused this BusinessException.
     */
    public BusinessException(String errorCode, Exception innerException) {
        super(innerException);
        this.errorCode = errorCode;
    }

    /**
     * Constructs a new BusinessException with an error code and an error description.
     *
     * @param errorCode        The error code associated with the exception.
     * @param errorDescription A description of the error.
     */
    public BusinessException(String errorCode, String errorDescription) {
        super();
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    /**
     * Constructs a new BusinessException with an error code, an error description, and an inner exception.
     *
     * @param errorCode        The error code associated with the exception.
     * @param errorDescription A description of the error.
     * @param innerException   The inner exception that caused this BusinessException.
     */
    public BusinessException(String errorCode, String errorDescription, Exception innerException) {
        super(innerException);
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    /**
     * Gets the error code associated with this BusinessException.
     *
     * @return The error code.
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code for this BusinessException.
     *
     * @param errorCode The error code to set.
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error description associated with this BusinessException.
     *
     * @return The error description.
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * Sets the error description for this BusinessException.
     *
     * @param errorDescription The error description to set.
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
