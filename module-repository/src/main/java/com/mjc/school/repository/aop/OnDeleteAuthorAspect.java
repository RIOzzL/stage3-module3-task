package com.mjc.school.repository.aop;

import com.mjc.school.repository.impl.NewsRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class OnDeleteAuthorAspect {

    @Autowired
    private NewsRepository newsRepository;

    @Pointcut("within(com.mjc.school.repository.impl.AuthorRepository) && @annotation(com.mjc.school.repository.aop.annotation.OnDelete)")
    public void hasOnDeleteAnnotation() {

    }

    @After(value = "hasOnDeleteAnnotation() && args(id)")
    public void onDeleteAnnotationProcessor(JoinPoint joinPoint, Long id) {
        newsRepository.readAll().stream().filter(news -> news.getAuthorId().equals(id))
                .forEach(news -> news.setAuthorId(null));
    }
}
