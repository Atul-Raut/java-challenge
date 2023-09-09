package jp.co.axa.apidemo.exception.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jp.co.axa.apidemo.common.CommonConstants;
import jp.co.axa.apidemo.exceptons.DataNotFoundException;
import jp.co.axa.apidemo.responses.ErrorResponse;
import jp.co.axa.apidemo.utils.AppUtils;

@ControllerAdvice
public class RestExceptionHandler {
    
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * Handles validation errors from the `MethodArgumentNotValidException`.
     *
     * @param ex The exception thrown during validation.
     * @return ResponseEntity containing a validation error response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        logger.error(CommonConstants.MSG_ERR_VALIDATION, ex);
        // Error list
        List<String> errors = new ArrayList<>();

        // Add validation error messages into error list.
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getDefaultMessage());
        }

        // Create error response object
        ErrorResponse errorResponse = new ErrorResponse(CommonConstants.CODE_ERR_VALIDATION,
                CommonConstants.MSG_ERR_VALIDATION, errors);
        return AppUtils.createErrorResponse(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    /**
     * Handles validation errors caused by `MethodArgumentTypeMismatchException`.
     *
     * @param ex The exception thrown when method argument types do not match.
     * @return ResponseEntity containing a validation error response.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentTypeMismatchException ex) {
        logger.error(CommonConstants.MSG_ERR_INVALID_PARAMETER, ex);
        // Create error response object
        ErrorResponse errorResponse = new ErrorResponse(CommonConstants.CODE_ERR_VALIDATION,
                CommonConstants.MSG_ERR_INVALID_PARAMETER, null);
        return AppUtils.createErrorResponse(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles `DataNotFoundException` by returning a 404 NOT FOUND response.
     *
     * @param ex The exception indicating that data was not found.
     * @return ResponseEntity containing a not found error response.
     */
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> dataNotFoundEHandler(DataNotFoundException ex) {
        logger.error(CommonConstants.MSG_ERR_DATA_NOT_FOUND, ex);
        // Create error response object
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(),
                ex.getErrorDescription(), null);
        return AppUtils.createErrorResponse(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles `EmptyResultDataAccessException` by returning a 404 NOT FOUND response.
     *
     * @param ex The exception indicating that no data was found.
     * @return ResponseEntity containing a not found error response.
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> dataNotFoundEHandler(EmptyResultDataAccessException ex) {
        logger.error(CommonConstants.MSG_ERR_DATA_NOT_FOUND, ex);
        // Create error response object
        ErrorResponse errorResponse = new ErrorResponse(CommonConstants.CODE_ERR_DATA_NOT_FOUND,
                CommonConstants.MSG_ERR_DATA_NOT_FOUND, null);
        return AppUtils.createErrorResponse(errorResponse, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handles generic exceptions by returning a 500 INTERNAL SERVER ERROR response.
     *
     * @param ex The generic exception.
     * @return ResponseEntity containing an internal server error response.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error(CommonConstants.MSG_ERR_UNEXPECTED, ex);
        // Create error response object
        ErrorResponse errorResponse = new ErrorResponse(CommonConstants.CODE_ERR_UNEXPECTED,
                CommonConstants.MSG_ERR_UNEXPECTED, null);
        return AppUtils.createErrorResponse(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    /**
     * Handles errors by returning a 500 INTERNAL SERVER ERROR response.
     *
     * @param ex The error.
     * @return ResponseEntity containing an internal server error response.
     */
    @ExceptionHandler(Error.class)
    public ResponseEntity<Object> handleError(Error ex) {
        logger.error(CommonConstants.MSG_ERR_UNEXPECTED, ex);
        // Create error response object
        ErrorResponse errorResponse = new ErrorResponse(CommonConstants.CODE_ERR_UNEXPECTED,
                CommonConstants.MSG_ERR_UNEXPECTED, null);
        return AppUtils.createErrorResponse(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}