package com.mjc.school.service.aop;

import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.aop.validator.AuthorDtoValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthorControllerValidationAspect {

    @Autowired
    private AuthorDtoValidator authorDtoValidator;

    @Pointcut("within(com.mjc.school.service.impl.AuthorService)")
    public void isAuthorService() {

    }

    @Pointcut("isAuthorService() && @annotation(com.mjc.school.service.aop.validator.restriction.UpdateValid)")
    public void hasUpdateValidAnnotation() {

    }

    @Before("hasUpdateValidAnnotation()")
    public void updateValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        authorDtoValidator.updateValidation((AuthorDto) args[0]);
    }

    @Pointcut("isAuthorService() && @annotation(com.mjc.school.service.aop.validator.restriction.CreateValid)")
    public void hasCreateValidAnnotation() {

    }

    @Before("hasCreateValidAnnotation()")
    public void createValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        authorDtoValidator.createValidation((AuthorDto) args[0]);
    }

    @Pointcut("isAuthorService() &&" +
            " execution(public * com.mjc.school.service.impl.*.*(..,@com.mjc.school.service.aop.validator.restriction.IsEntityExist (*),..))")
    public void hasIsEntityExistAnnotation() {

    }

    @Before("hasIsEntityExistAnnotation() && args(id)")
    public void IsEntityExistAnnotationProcessor(JoinPoint joinPoint, Long id) {
        authorDtoValidator.isExistValidation(id);
    }
}
