package com.skylab.core.database;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "firebirdEntityManagerFactory",
        transactionManagerRef = "firebirdTransactionManager",
        basePackages = "com.skylab.domain.mdrp.repository")
public class FirebirdConfiguration {

    @Bean
    @Primary

    @ConfigurationProperties(prefix = "mdrp.datasource")
    public DataSourceProperties firebirdDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource firebirdDataSource(@Qualifier("firebirdDataSourceProperties")
                                          DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "firebirdEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean firebirdEntityManagerFactory(
            @Qualifier("firebirdDataSource") DataSource firebirdDataSource,
            EntityManagerFactoryBuilder builder) {

        return builder.dataSource(firebirdDataSource)
                .packages("com.skylab.domain.mdrp.model")
                .persistenceUnit("mdrp")
                .build();

    }

    @Bean
    @Primary
    public PlatformTransactionManager firebirdTransactionManager(
            @Qualifier("firebirdEntityManagerFactory")
            EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
