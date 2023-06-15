package com.mjc.school.service.aop;

import com.mjc.school.service.aop.validator.TagValidator;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.TagDto;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TagValidationAspect {

    @Autowired
    private TagValidator tagValidator;

    @Pointcut("within(com.mjc.school.service.impl.TagService)")
    public void isTagService() {
    }


    @Pointcut("isTagService() && @annotation(com.mjc.school.service.aop.validator.restriction.CreateValid)")
    public void hasCreateValidAnnotation() {
    }

    @Before(value = "hasCreateValidAnnotation()")
    public void createValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        tagValidator.createValidation((TagDto) args[0]);
    }

    @Pointcut("isTagService() && @annotation(com.mjc.school.service.aop.validator.restriction.UpdateValid)")
    public void hasUpdateValidAnnotation() {
    }

    @Before(value = "hasUpdateValidAnnotation()")
    public void updateValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        tagValidator.updateValidation((TagDto) args[0]);
    }

    @Pointcut("isTagService() &&" +
            " execution(public * com.mjc.school.service.impl.*.*(..,@com.mjc.school.service.aop.validator.restriction.IsEntityExist (*),..))")
    public void hasIsEntityExistAnnotation() {

    }

    @Before("hasIsEntityExistAnnotation() && args(id)")
    public void IsEntityExistAnnotationProcessor(JoinPoint joinPoint, Long id) {
        tagValidator.isExistValidation(id);
    }

}
