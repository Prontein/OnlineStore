package ru.geekbrains.web.utils;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;


@Aspect
@Component
@Data
public class AppLoggingAspect {
    //    private final StatisticMessageDTO statisticMessageDTO;
    private ConcurrentHashMap<String, Long> serviceMap;

    private static Logger logger = LoggerFactory.getLogger(AppLoggingAspect.class);
    long durationCartService;
    long durationOrderService;
    long durationProductService;
    long durationUserService;

    @PostConstruct
    public void initServiceMap() {
        this.serviceMap = new ConcurrentHashMap<>();
    }

    @Around("execution(public * ru.geekbrains.web.service.*Service.*(..))")
    public Object servicesStats(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        setServiceMap(proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName(), duration);

        return out;
    }

    public void setServiceMap(String serviceName, long duration) {
        if (serviceMap.containsKey(serviceName)) {
            serviceMap.put(serviceName, serviceMap.get(serviceName) + duration);
        } else {
            serviceMap.put(serviceName, duration);
        }
        logger.info("Продолжительность работы " + serviceName + ": " + duration + " ms" +
                "\tОбщее время работы " + serviceName +": " + serviceMap.get(serviceName) + " ms");
    }
}