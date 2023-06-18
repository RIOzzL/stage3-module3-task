package com.mjc.school.repository.model.entity;

import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(News.class)
public abstract class News_ {

	public static volatile SingularAttribute<News, LocalDateTime> lastUpdateDate;
	public static volatile SingularAttribute<News, Author> author;
	public static volatile SingularAttribute<News, Long> id;
	public static volatile SingularAttribute<News, String> title;
	public static volatile SingularAttribute<News, String> content;
	public static volatile SingularAttribute<News, LocalDateTime> createDate;
	public static volatile SetAttribute<News, Tag> tags;

	public static final String LAST_UPDATE_DATE = "lastUpdateDate";
	public static final String AUTHOR = "author";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String CREATE_DATE = "createDate";
	public static final String TAGS = "tags";

}

