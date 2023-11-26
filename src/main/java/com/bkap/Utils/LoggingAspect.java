package com.bkap.Utils;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.bkap.Utils.annotations.LogExecutionTime)")
    public void logPointcut(){

    }
    @Before("logPointcut()")
    public void logMethodBeforeAdvice(){
        logger.info("EXECUTING SPRING AOP BEFORE ADVICE !>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @AfterThrowing("logPointcut()")
    public void logMethodExceptionAdvice(){
        logger.info("EXECUTING SPRING AOP EXCEPTION HANDLER !>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @Around("logPointcut()")
    public void logMethodAroundAdvice(){
        logger.info("EXECUTING SPRING AOP AROUND ADVICE !>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}