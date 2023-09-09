package jp.co.axa.apidemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Execution start and end log
 * 
 * This aspect is responsible for logging the start and end of method executions within
 * the EmployeeServiceImpl class.
 * 
 * @author rautatul
 */
@Component
@Aspect
public class LogAspect {

	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@Before("within(jp.co.axa.apidemo.services.EmployeeServiceImpl) && execution(* *(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		logger.info("Execution started for : " + methodName);
    }

    @After("within(jp.co.axa.apidemo.services.EmployeeServiceImpl) && execution(* *(..))")
    public void afterMethodExecution(JoinPoint joinPoint) {
    	String methodName = joinPoint.getSignature().getName();
		logger.info("Execution finished for : " + methodName);
    }
}
