package com.mjc.school.repository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EntityScan(basePackages = "com.mjc.school.repository.model.entity")
@EnableTransactionManagement
@EnableJpaRepositories("com.mjc.school.repository.impl")
@PropertySource("classpath:application.properties")
public class RepositoryConfig {

    private static final String DATABASE_DRIVER = "spring.datasource.driver-class-name";
    private static final String DATABASE_URL = "spring.datasource.url";
    private static final String DATABASE_USERNAME = "spring.datasource.username";
    private static final String DATABASE_PASSWORD = "spring.datasource.password";

    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_SHOWSQL = "hibernate.show_sql";
    private static final String HIBERNATE_FORMATSQL = "hibernate.format_sql";
    private static final String HIBERNATE_HBM2DDL = "hibernate.hbm2ddl.auto";

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty(DATABASE_DRIVER)));
        dataSource.setUrl(environment.getProperty(DATABASE_URL));
        dataSource.setUsername(environment.getProperty(DATABASE_USERNAME));
        dataSource.setPassword(environment.getProperty(DATABASE_PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //    vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setPackagesToScan("com.mjc.school.*");
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setJpaProperties(hibernateProperties());
        return entityManagerFactory;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    final Properties hibernateProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(HIBERNATE_HBM2DDL, environment.getProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));
        hibernateProperties.setProperty(HIBERNATE_DIALECT, environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        hibernateProperties.setProperty(HIBERNATE_SHOWSQL, environment.getProperty("spring.jpa.show-sql"));
        // hibernateProperties.setProperty("hibernate.sql.init.mode", environment.getProperty("spring.sql.init.mode"));
        // hibernateProperties.setProperty("hibernate.hbm2ddl.import_files", environment.getProperty("spring.sql.init.data-locations"));
        // hibernateProperties.setProperty("hibernate.auto_quote_keyword", environment.getProperty("spring.jpa.properties.hibernate.auto_quote_keyword"));

        return hibernateProperties;
    }

//    @Bean
//    public TransactionTemplate tx(PlatformTransactionManager transactionManager) {
//        return new TransactionTemplate(transactionManager);
//    }

}