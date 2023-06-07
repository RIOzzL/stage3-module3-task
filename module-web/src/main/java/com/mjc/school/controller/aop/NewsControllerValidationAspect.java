package com.mjc.school.controller.aop;

import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.validator.NewsDtoValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NewsControllerValidationAspect {

    @Autowired
    private NewsDtoValidator newsDtoValidator;

    @Pointcut("within(com.mjc.school.controller.impl.NewsController)")
    public void isNewsController() {

    }

    @Pointcut("isNewsController() && @annotation(com.mjc.school.service.validator.restriction.UpdateValid)")
    public void hasUpdateValidAnnotation() {

    }

    @Before("hasUpdateValidAnnotation()")
    public void updateValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        newsDtoValidator.updateValidation((NewsDto) args[0]);
    }

    @Pointcut("isNewsController() && @annotation(com.mjc.school.service.validator.restriction.CreateValid)")
    public void hasCreateValidAnnotation() {

    }

    @Before("hasCreateValidAnnotation()")
    public void createValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        newsDtoValidator.createValidation((NewsDto) args[0]);
    }

    @Pointcut("isNewsController() && @args(com.mjc.school.service.validator.restriction.IsEntityExist,..)")
    public void hasIsEntityExistAnnotation() {

    }

    @Before("hasIsEntityExistAnnotation() && args(id)")
    public void IsEntityExistAnnotationProcessor(JoinPoint joinPoint, Long id) {
        newsDtoValidator.isExistValidation(id);
    }
}
