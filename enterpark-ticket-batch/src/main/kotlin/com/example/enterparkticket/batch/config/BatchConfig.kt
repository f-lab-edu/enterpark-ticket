package com.example.enterparkticket.batch.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.support.JdbcTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class BatchConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.batch")
    fun batchDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Primary
    @Bean
    fun batchTransactionManager(
        @Qualifier("batchDataSource") dataSource: DataSource,
    ): PlatformTransactionManager {
        return JdbcTransactionManager(dataSource)
    }

    companion object {

        @Bean
        fun jobRegistryBeanPostProcessorRemover(): BeanDefinitionRegistryPostProcessor {
            return BeanDefinitionRegistryPostProcessor { registry ->
                registry.removeBeanDefinition("jobRegistryBeanPostProcessor")
            }
        }
    }
}
