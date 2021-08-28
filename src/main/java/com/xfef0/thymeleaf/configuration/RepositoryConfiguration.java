package com.xfef0.thymeleaf.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.xfef0.thymeleaf.domain"})
@EnableJpaRepositories(basePackages = {"com.xfef0.thymeleaf.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
