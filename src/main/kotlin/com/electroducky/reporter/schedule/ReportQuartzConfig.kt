package com.electroducky.reporter.schedule

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SpringBeanJobFactory
import java.util.*
import javax.sql.DataSource


@Configuration
class ReportQuartzConfig {

    @Bean
    fun springBeanJobFactory(applicationContext: ApplicationContext): SpringBeanJobFactory {
        val jobFactory = AutowiringSpringBeanJobFactory()
        jobFactory.setApplicationContext(applicationContext)
        return jobFactory
    }

    @Bean
    fun scheduler(springBeanJobFactory: SpringBeanJobFactory, dataSource: DataSource): SchedulerFactoryBean {
        val schedulerFactory = SchedulerFactoryBean()
        val properties = Properties()
        properties.setProperty("org.quartz.scheduler.instanceName", "ReportQuartzInstance")
        properties.setProperty("org.quartz.scheduler.instanceId", "ReportQuartzInstance-1")
        schedulerFactory.setOverwriteExistingJobs(true)
        schedulerFactory.isAutoStartup = true
        schedulerFactory.setQuartzProperties(properties)
        schedulerFactory.setDataSource(dataSource)
        schedulerFactory.setJobFactory(springBeanJobFactory)
        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true)
        return schedulerFactory
    }
}