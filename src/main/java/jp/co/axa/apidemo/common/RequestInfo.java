package jp.co.axa.apidemo.common;

import java.util.Date;

/**
 * Represents request-related information, including a tracking ID and request timestamp.
 * @author rautatul
 */
public class RequestInfo {

	//This private field represents the tracking ID associated with a request.
	//The tracking ID is a unique identifier used to trace and identify individual requests.
	private String trackingId;
	//This private field represents the timestamp of when the request was created or initiated. 
	//It stores the time when the request object was created.
	private Date requestTime;

	/**
     * Default constructor that initializes the requestTime field with the current date and time.
     */
	public RequestInfo() {
		requestTime = new Date();
	}
	
	/**
     * Gets the tracking ID associated with the request.
     *
     * @return The tracking ID.
     */
	public String getTrackingId() {
		return trackingId;
	}

	/**
     * Sets the tracking ID associated with the request.
     *
     * @param trackingId The tracking ID to set.
     */
	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}

	/**
     * Gets the timestamp when the request was created.
     *
     * @return The request timestamp.
     */
	public Date getRequestTime() {
		return requestTime;
	}
	
}
