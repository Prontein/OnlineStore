package ru.geekbrains.web.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;


@Aspect
@Component
public class AppLoggingAspect {
    private static Logger logger = LoggerFactory.getLogger(AppLoggingAspect.class);
    long durationCartService;
    long durationOrderService;
    long durationProductService;
    long durationUserService;

    @Around("execution(public * ru.geekbrains.web.service.CartService.*(..))")
    public Object cartMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        durationCartService += duration;
        logger.info("Продолжительность работы CartService: " + duration + " ms" + "\tОбщее время работы CartService: " + durationCartService + " ms");
        return out;
    }

    @Around("execution(public * ru.geekbrains.web.service.OrderService.*(..))")
    public Object orderMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        durationOrderService += duration;
        logger.info("Продолжительность работы OrderService: " + duration + " ms" + "\tОбщее время работы OrderService: " + durationOrderService + " ms");
        return out;
    }

    @Around("execution(public * ru.geekbrains.web.service.ProductService.*(..))")
    public Object productMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        durationProductService += duration;
        logger.info("Продолжительность работы ProductService: " + duration + " ms" + "\tОбщее время работы ProductService: " + durationProductService + " ms");
        return out;
    }

    @Around("execution(public * ru.geekbrains.web.service.UserService.*(..))")
    public Object userMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        durationUserService += duration;
        logger.info("Продолжительность работы UserService: " + duration + " ms" + "\tОбщее время работы UserService: " + durationUserService + " ms");
        return out;
    }
}
