package com.ispace.usermanagement.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandler {

    @Around("execution(* com.ispace.usermanagement.rest.*.*(..))")
    public ResponseEntity handleGlobalException(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return  (ResponseEntity)proceedingJoinPoint.proceed();
        }catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}