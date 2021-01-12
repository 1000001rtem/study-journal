package ru.eremin.studytableback.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import ru.eremin.studytableback.filter.JwtTokenFilter
import ru.eremin.studytableback.security.JwtTokenProvider
import ru.eremin.studytableback.security.service.AuthenticationService
import ru.eremin.studytableback.security.service.UserService

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
    private val userService: UserService,
    private val jwtTokenProvider: JwtTokenProvider
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun authenticationService() = AuthenticationService(userService, jwtTokenProvider, passwordEncoder())

    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/v1/auth", "/v1/registration")
    }

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .csrf()
            .disable()
            .addFilterBefore(
                JwtTokenFilter(authenticationService()),
                UsernamePasswordAuthenticationFilter::class.java
            )
    }
}