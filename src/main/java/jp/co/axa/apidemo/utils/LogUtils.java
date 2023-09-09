package jp.co.axa.apidemo.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import jp.co.axa.apidemo.common.RequestInfo;
import jp.co.axa.apidemo.common.RequestInfoContext;

/**
 * LogUtils
 * @author rautatul
 *
 */
public class LogUtils {
	
	/**
	 * Logs an informational message using the provided logger with additional details.
	 * 
	 * @param logger  The logger instance to use for logging.
	 * @param message The message to be logged.
	 */
	public static void info(Logger logger, String message) {
		logger.info("{} {}", getTrackingId(), StringUtils.normalizeSpace(message));
	}
	
	/**
	 * Logs a debug message using the provided logger with additional details.
	 * 
	 * @param logger  The logger instance to use for debugging.
	 * @param message The debug message to be logged.
	 */
	public static void debug(Logger logger, String message) {
		logger.debug("{} {}", getTrackingId(), StringUtils.normalizeSpace(message));
	}
	
	/**
	 * Logs an error message using the provided logger with additional details.
	 * 
	 * @param logger  The logger instance to use for logging errors.
	 * @param message The error message to be logged.
	 */
	public static void error(Logger logger, String message) {
		logger.error("{} {}", getTrackingId(), StringUtils.normalizeSpace(message));
	}
	
	/**
	 * Logs an error message using the provided logger with additional details including a code.
	 * 
	 * @param logger  The logger instance to use for logging errors.
	 * @param code    The error code associated with the error.
	 * @param message The error message to be logged.
	 */
	public static void error(Logger logger, String code, String message) {
		logger.error("{} {} {}", getTrackingId(), StringUtils.normalizeSpace(code), StringUtils.normalizeSpace(message));
	}
	
	/**
	 * Logs an error message along with a Throwable (exception) using the provided logger.
	 * 
	 * @param logger  The logger instance to use for logging errors.
	 * @param message The error message to be logged.
	 * @param tx      The Throwable (exception) associated with the error.
	 */
	public static void error(Logger logger, String message, Throwable tx) {
		logger.error("{} {}", getTrackingId(), StringUtils.normalizeSpace(message), tx);
	}
	
	/**
	 * Logs an error message along with an error code and a Throwable (exception) using the provided logger.
	 *
	 * @param logger  The logger instance to use for logging errors.
	 * @param code    The error code associated with the error.
	 * @param message The error message to be logged.
	 * @param tx      The Throwable (exception) associated with the error.
	 */
	public static void error(Logger logger, String code, String message, Throwable tx) {
		logger.error("{} {} {}", getTrackingId(), StringUtils.normalizeSpace(code), StringUtils.normalizeSpace(message), tx);
	}
	
	
	/**
	 * Retrieves and returns the tracking ID from the current request's context.
	 * If no tracking ID is available, or if it's empty or consists of whitespace characters,
	 * an empty string is returned.
	 *
	 * @return The tracking ID as a normalized string or an empty string if not available.
	 */
	public static String getTrackingId() {
		RequestInfo requestInfo = RequestInfoContext.getRequestInfo();
		if(null == requestInfo) {
			return "";
		}
		
		return StringUtils.normalizeSpace(requestInfo.getTrackingId());
	}
}
