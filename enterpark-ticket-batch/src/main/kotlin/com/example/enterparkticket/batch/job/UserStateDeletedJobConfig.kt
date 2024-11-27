package com.example.enterparkticket.batch.job

import com.example.enterparkticket.core.domain.config.jpa.JpaConfig
import com.example.enterparkticket.core.domain.config.jpa.JpaConfig.Companion.PRIMARY_ENTITY_MANAGER_FACTORY
import com.example.enterparkticket.core.domain.config.jpa.JpaConfig.Companion.PRIMARY_TRANSACTION_MANAGER
import com.example.enterparkticket.core.domain.user.domain.User
import jakarta.persistence.EntityManagerFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.database.JpaItemWriter
import org.springframework.batch.item.database.JpaPagingItemReader
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.transaction.PlatformTransactionManager
import java.time.LocalDate

@Configuration
@Import(JpaConfig::class)
class UserStateDeletedJobConfig(

    @Value("\${spring.batch.chunk-size}")
    private val chunkSize: Int,

    @Qualifier(PRIMARY_ENTITY_MANAGER_FACTORY)
    private val entityManagerFactory: EntityManagerFactory,

    @Qualifier(PRIMARY_TRANSACTION_MANAGER)
    private val transactionManager: PlatformTransactionManager,

    private val jobRepository: JobRepository,
) {

    @Bean(JOB_NAME)
    fun job(): Job {
        return JobBuilder(JOB_NAME, jobRepository)
            .start(step())
            .build()
    }

    @Bean(BEAN_PREFIX + "step")
    @JobScope
    fun step(): Step {
        return StepBuilder(BEAN_PREFIX + "step", jobRepository)
            .chunk<User, User>(chunkSize, transactionManager)
            .reader(itemReader(null))
            .processor(itemProcessor())
            .writer(itemWriter())
            .build()
    }

    @Bean(BEAN_PREFIX + "itemReader")
    @StepScope
    fun itemReader(@Value("#{jobParameters[date]}") date: LocalDate?): JpaPagingItemReader<User> {
        return JpaPagingItemReaderBuilder<User>()
            .name(BEAN_PREFIX + "itemReader")
            .entityManagerFactory(entityManagerFactory)
            .pageSize(chunkSize)
            .queryString("SELECT u FROM User u WHERE DATE(u.deletedDate) < :date ORDER BY u.id ASC")
            .parameterValues(mapOf("date" to date?.minusDays(7)))
            .build()
    }

    @Bean(BEAN_PREFIX + "itemProcessor")
    fun itemProcessor(): ItemProcessor<User, User> {
        return ItemProcessor {
            it.deleteUser()
        }
    }

    @Bean(BEAN_PREFIX + "itemWriter")
    fun itemWriter(): JpaItemWriter<User> {
        return JpaItemWriter<User>().apply {
            setEntityManagerFactory(entityManagerFactory)
        }
    }

    companion object {
        const val JOB_NAME = "유저상태탈퇴"
        const val BEAN_PREFIX = JOB_NAME + "_"
    }
}
