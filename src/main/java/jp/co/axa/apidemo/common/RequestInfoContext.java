package jp.co.axa.apidemo.common;

/**
 * A utility class for managing request-specific information in a thread-local context.
 * This class provides methods to set, retrieve, and clear request information.
 */
public class RequestInfoContext {
    private static final ThreadLocal<RequestInfo> requestInfoThreadLocal = new ThreadLocal<>();

    /**
     * Sets the provided request information into thread-local storage.
     * This method is used to store request-specific data for the current thread.
     *
     * @param requestInfo The request information to be stored.
     */
    public static void setRequestInfo(RequestInfo requestInfo) {
    	requestInfoThreadLocal.set(requestInfo);
    }

    /**
     * Retrieves the request information stored in thread-local storage for the current thread.
     *
     * @return The request information associated with the current thread, or null if not set.
     */
    public static RequestInfo getRequestInfo() {
        return requestInfoThreadLocal.get();
    }

    /**
     * Clears the thread-local storage associated with the request information.
     * This method is typically used to clean up request-specific data after processing.
     */
    public static void clear() {
    	requestInfoThreadLocal.remove();
    }
}