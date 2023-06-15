package com.mjc.school.service.aop;

import com.mjc.school.service.aop.validator.NewsDtoValidator;
import com.mjc.school.service.dto.NewsDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NewsValidationAspect {

    @Autowired
    private NewsDtoValidator newsDtoValidator;

    @Pointcut("within(com.mjc.school.service.impl.NewsService)")
    public void isNewsService() {

    }

    @Pointcut("isNewsService() && @annotation(com.mjc.school.service.aop.validator.restriction.UpdateValid)")
    public void hasUpdateValidAnnotation() {

    }

    @Before("hasUpdateValidAnnotation()")
    public void updateValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        newsDtoValidator.updateValidation((NewsDto) args[0]);
    }

    @Pointcut("isNewsService() && @annotation(com.mjc.school.service.aop.validator.restriction.CreateValid)")
    public void hasCreateValidAnnotation() {

    }

    @Before("hasCreateValidAnnotation()")
    public void createValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        newsDtoValidator.createValidation((NewsDto) args[0]);
    }

    @Pointcut("isNewsService() &&" +
            " execution(public * com.mjc.school.service.impl.*.*(..,@com.mjc.school.service.aop.validator.restriction.IsEntityExist (*),..))")
    public void hasIsEntityExistAnnotation() {

    }

    @Before("hasIsEntityExistAnnotation() && args(id)")
    public void IsEntityExistAnnotationProcessor(JoinPoint joinPoint, Long id) {
        newsDtoValidator.isExistValidation(id);
    }
}
