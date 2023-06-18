package com.mjc.school.repository.model.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tag.class)
public abstract class Tag_ {

	public static volatile ListAttribute<Tag, News> news;
	public static volatile SingularAttribute<Tag, String> name;
	public static volatile SingularAttribute<Tag, Long> id;

	public static final String NEWS = "news";
	public static final String NAME = "name";
	public static final String ID = "id";

}

