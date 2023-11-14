package com.codegym.aurora.logger;

import com.codegym.aurora.payload.request.NumerologyReportRequestDTO;
import com.codegym.aurora.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class NumerologyLog {
    public static final Logger LOGGER = LogManager.getLogger(NumerologyLog.class);
    private final UserService userService;

    @Pointcut("execution(public * com.codegym.aurora.service.NumerologyReportService.createNumerologyReport(..))")
    public void numerologyMethods() {
    }

    @AfterReturning(value = "numerologyMethods()")
    public void configLogging(JoinPoint joinPoint) {
        NumerologyReportRequestDTO arg = (NumerologyReportRequestDTO) joinPoint.getArgs()[0];
        String user = userService.getCurrentUsername();
        String reportType = Boolean.TRUE.equals(arg.getVip()) ? "VIP" : "free";
        LOGGER.info(String.format("```diff%n!##User [%s] created a **%s** numerology report!```", user, reportType));
    }
}
