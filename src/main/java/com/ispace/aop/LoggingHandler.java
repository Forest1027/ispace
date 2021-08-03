package com.ispace.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.util.Arrays;

@Aspect
@Component
public class LoggingHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHandler.class);

    @Before("execution(* com.ispace.rest.*.*(..))")
    public void logRequests(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        try {
            HttpServletRequest request = attributes.getRequest();
            LOGGER.debug(String.format("REST request ===> %s  %s, in class ===> %s with parameter ===> %s", request.getMethod(),
                    request.getRequestURL(), joinPoint.getSignature().getDeclaringTypeName(), Arrays.toString(joinPoint.getArgs())));
        } catch (NullPointerException exception) {
            LOGGER.debug(String.format("REST request (CAN'T GET INFO), in class ===> %s with parameter ===> %s", joinPoint.getSignature().getDeclaringTypeName(), Arrays.toString(joinPoint.getArgs())));
        }

    }

    @AfterReturning(pointcut = "execution(* com.ispace.rest.*.*(..))", returning = "result")
    public void logResponse(Object result) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            LOGGER.debug(String.format("REST response ===> %s, for request ===> %s  %s", result.toString(), request.getMethod(), request.getRequestURL()));
        } catch (NullPointerException exception) {
            LOGGER.debug(String.format("REST response ===> %s, (CAN'T GET INFO))", result.toString()));
        }

    }
}
