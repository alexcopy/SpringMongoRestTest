package ru.gpsbox.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

//@Configuration
//@PropertySource("classpath:postgres.properties")
//@EnableJpaRepositories(basePackages = "ru.gpsbox.test.repository.PostgreDAO", entityManagerFactoryRef = "postgreEntityManager", transactionManagerRef = "postgreTransactionManager")
public class PostgreConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean postgreEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgreDataSource());
        em.setPackagesToScan(new String[] { "ru.gpsbox.test.domain.postgresql" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        em.setJpaPropertyMap(properties);
        return em;
    }
    @Bean
    public DataSource postgreDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.postgresql.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.postgresql.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.postgresql.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.postgresql.datasource.password"));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager postgreTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgreEntityManager().getObject());
        return transactionManager;
    }
}
