package com.codegym.aurora.logger;

import com.codegym.aurora.payload.request.RegisterRequestDTO;
import com.codegym.aurora.payload.response.ResponseDTO;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserLogs {
    public static final Logger LOGGER = LogManager.getLogger(UserLogs.class);

    @Pointcut("execution(public * com.codegym.aurora.service.UserService.register(..))")
    public void registrationMethods() {
    }

    @Around(value = "registrationMethods()", argNames = "joinPoint")
    public ResponseDTO configLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        RegisterRequestDTO arg = (RegisterRequestDTO) joinPoint.getArgs()[0];
        ResponseDTO result = (ResponseDTO) joinPoint.proceed();

        if (result.getStatus().equals(HttpStatus.CREATED)) {
            LOGGER.info(String.format("##New user registered!... - username: %s, email: %s",
                    arg.getUsername(),
                    arg.getEmail()));
        }

        return result;
    }
}
