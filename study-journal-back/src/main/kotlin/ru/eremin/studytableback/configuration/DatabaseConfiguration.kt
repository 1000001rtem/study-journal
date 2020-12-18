package ru.eremin.studytableback.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing


@Configuration
@EnableJpaAuditing(auditorAwareRef = "userFieldAuditorAware")
class DatabaseConfiguration {

    @Bean
    fun userFieldAuditorAware(): AuditorAware<String> = UserAuditorAware()

}