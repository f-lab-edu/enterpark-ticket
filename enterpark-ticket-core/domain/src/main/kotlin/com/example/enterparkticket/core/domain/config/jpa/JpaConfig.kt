package com.example.enterparkticket.core.domain.config.jpa

import com.example.enterparkticket.core.domain.common.consts.EnterparkTicketConsts.PRIMARY_TRANSACTION_MANAGER
import com.example.enterparkticket.core.domain.config.EnterparkTicketConfig
import com.example.enterparkticket.core.domain.config.jpa.JpaConfig.Companion.PRIMARY_ENTITY_MANAGER_FACTORY
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@EnableTransactionManagement
@EntityScan(basePackages = ["com.example.enterparkticket"])
@EnableJpaRepositories(
    basePackages = ["com.example.enterparkticket"],
    entityManagerFactoryRef = PRIMARY_ENTITY_MANAGER_FACTORY,
    transactionManagerRef = PRIMARY_TRANSACTION_MANAGER,
)
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
class JpaConfig(
    private val jpaProperties: JpaProperties,
    private val hibernateProperties: HibernateProperties,
) : EnterparkTicketConfig {

    @Bean
    @ConfigurationProperties(prefix = PRIMARY_CONFIGURATION_PROPERTIES_PREFIX)
    fun primaryDataSource(): DataSource {
        return DataSourceBuilder.create().build()
    }

    @Bean
    fun primaryEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier(PRIMARY_DATASOURCE) dataSource: DataSource,
    ): LocalContainerEntityManagerFactoryBean {
        val properties = hibernateProperties.determineHibernateProperties(
            jpaProperties.properties,
            HibernateSettings()
        )
        return builder.dataSource(dataSource)
            .packages("com.example.enterparkticket")
            .persistenceUnit(PRIMARY_PERSISTENCE_UNIT)
            .properties(properties)
            .build()
    }

    @Bean
    fun primaryTransactionManager(
        @Qualifier(PRIMARY_ENTITY_MANAGER_FACTORY) entityManagerFactory: EntityManagerFactory,
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    @Bean
    fun securityAuditorAware(auditorProvider: AuditorProvider): AuditorAware<String> {
        return SecurityAuditorAware(auditorProvider)
    }

    companion object {
        const val PRIMARY_CONFIGURATION_PROPERTIES_PREFIX = "spring.datasource.primary"
        const val PRIMARY_PERSISTENCE_UNIT = "primaryPersistenceUnit"
        const val PRIMARY_DATASOURCE = "primaryDataSource"
        const val PRIMARY_ENTITY_MANAGER_FACTORY = "primaryEntityManagerFactory"
    }
}
