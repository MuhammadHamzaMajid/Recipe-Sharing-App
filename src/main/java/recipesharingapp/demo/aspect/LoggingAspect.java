package recipesharingapp.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* recipesharingapp.demo.controller.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        logger.info("Entering method: {} with arguments: {}", methodName, args);
    }

    @After("execution(* recipesharingapp.demo.controller.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();

        logger.info("Exiting method: {}", methodName);
    }

    @AfterThrowing(pointcut = "execution(* recipesharingapp.demo.controller.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().toShortString();

        logger.error("Exception in method: {} with message: {}", methodName, exception.getMessage());
    }
}
