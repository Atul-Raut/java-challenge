package jp.co.axa.apidemo.exceptons;

public class DataNotFoundException extends BusinessException {
	private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DataNotFoundException with only an error code.
     *
     * @param errorCode The error code associated with the exception.
     */
    public DataNotFoundException(String errorCode) {
        super(errorCode);
    }

    /**
     * Constructs a new DataNotFoundException with an error code and an inner exception.
     *
     * @param errorCode      The error code associated with the exception.
     * @param innerException The inner exception that caused this DataNotFoundException.
     */
    public DataNotFoundException(String errorCode, Exception innerException) {
        super(errorCode, innerException);
    }

    /**
     * Constructs a new DataNotFoundException with an error code and an error description.
     *
     * @param errorCode        The error code associated with the exception.
     * @param errorDescription A description of the error.
     */
    public DataNotFoundException(String errorCode, String errorDescription) {
        super(errorCode, errorDescription);
    }

    /**
     * Constructs a new DataNotFoundException with an error code, an error description, and an inner exception.
     *
     * @param errorCode        The error code associated with the exception.
     * @param errorDescription A description of the error.
     * @param innerException   The inner exception that caused this DataNotFoundException.
     */
    public DataNotFoundException(String errorCode, String errorDescription, Exception innerException) {
        super(errorCode, errorDescription, innerException);
    }
}
