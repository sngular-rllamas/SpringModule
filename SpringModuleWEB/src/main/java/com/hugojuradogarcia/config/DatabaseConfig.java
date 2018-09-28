package com.hugojuradogarcia.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(basePackages = "com.hugojuradogarcia.dao", entityManagerFactoryRef = "entityManagerFactoryBean")
public class DatabaseConfig {
    private final String PROPERTY_DRIVER = "jdbc.driverClassName";
    private final String PROPERTY_URL = "jdbc.url";
    private final String PROPERTY_USERNAME = "jdbc.user";
    private final String PROPERTY_PASS = "jdbc.password";
    private final String PROPERTY_SHOW_SQL = "hibernate.show_sql";
    private final String PROPERTY_DIALECT = "hibernate.dialect";
    private final String PROPERTY_HBM2DDL = "hibernate.hbm2ddl.auto";
    private final String PROPERTY_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

    @Autowired
    Environment environment;

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
        lfb.setDataSource(dataSource());
        lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        lfb.setPackagesToScan(environment.getProperty(PROPERTY_PACKAGES_TO_SCAN));
        lfb.setJpaProperties(hibernateProps());
        return lfb;
    }

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(environment.getProperty(PROPERTY_URL));
        ds.setUsername(environment.getProperty(PROPERTY_USERNAME));
        ds.setPassword(environment.getProperty(PROPERTY_PASS));
        ds.setDriverClassName(environment.getProperty(PROPERTY_DRIVER));
        return ds;
    }

    Properties hibernateProps() {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_DIALECT, environment.getProperty(PROPERTY_DIALECT));
        properties.setProperty(PROPERTY_SHOW_SQL, environment.getProperty(PROPERTY_SHOW_SQL));
        properties.setProperty(PROPERTY_HBM2DDL, environment.getProperty(PROPERTY_HBM2DDL));
        return properties;
    }

    @Bean
    JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
