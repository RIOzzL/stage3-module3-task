package com.mjc.school.repository.aop;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.entity.Author;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class OnDeleteAuthorAspect {

    @Autowired
    private AuthorRepository authorRepository;

    @Pointcut("within(com.mjc.school.repository.impl.AuthorRepository) && @annotation(com.mjc.school.repository.aop.annotation.OnDelete)")
    public void hasOnDeleteAnnotation() {

    }

    @Before(value = "hasOnDeleteAnnotation() && args(id)")
    public void onDeleteAnnotationProcessor(JoinPoint joinPoint, Long id) {
        Author author = authorRepository.readById(id).get();
        author.getNews().forEach(news -> news.setAuthor(null));
    }
}
