package com.codegym.aurora.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ServiceLogger {
    public static final Logger LOGGER = LogManager.getLogger(ServiceLogger.class);

    @Pointcut("execution(public * com.codegym.aurora.service.*.*(..))")
    public void serviceMethods() {
    }

    @AfterThrowing(value = "serviceMethods()", throwing = "e")
    public void serviceLogging(JoinPoint joinPoint, Exception e) {
        LOGGER.warn(String.format("EXCEPTION: %s.%s() - ARGS: %s",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs())));
        LOGGER.error("||[" + e + "]||");
    }
}
