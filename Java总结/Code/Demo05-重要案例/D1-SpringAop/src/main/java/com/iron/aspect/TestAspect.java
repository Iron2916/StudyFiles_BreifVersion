package com.iron.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect
{
    @Before("execution(public void com.iron.service.impl.TestServiceImpl.Test(..))")
    public void beforeNotify()
    {
        System.out.println("-----@Before前置通知");
    }
    @After("execution(public void com.iron.service.impl.TestServiceImpl.Test(..))")
    public void afterNotify()
    {
        System.out.println("-----@After后置通知");
    }
    @AfterReturning("execution(public void com.iron.service.impl.TestServiceImpl.Test(..))")
    public void afterReturningNotify()
    {
        System.out.println("-----@AfterReturning返回通知");
    }
    @AfterThrowing("execution(public void com.iron.service.impl.TestServiceImpl.Test(..))")
    public void afterThrowingNotify()
    {
        System.out.println("-----@AfterThrowing异常通知");
    }

    @Around("execution(public void com.iron.service.impl.TestServiceImpl.Test(..))")
    public Object aroundNotify(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        Object retValue = null;

        System.out.println("-----@Around环绕通知AAA");
        retValue = proceedingJoinPoint.proceed();//放行
        System.out.println("-----@Around环绕通知BBB");

        return retValue;
    }
}
