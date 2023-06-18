package com.mjc.school.repository.model.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Author.class)
public abstract class Author_ {

	public static volatile ListAttribute<Author, News> news;
	public static volatile SingularAttribute<Author, LocalDateTime> lastUpdateDate;
	public static volatile SingularAttribute<Author, String> name;
	public static volatile SingularAttribute<Author, Long> id;
	public static volatile SingularAttribute<Author, LocalDateTime> createDate;

	public static final String NEWS = "news";
	public static final String LAST_UPDATE_DATE = "lastUpdateDate";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String CREATE_DATE = "createDate";

}

