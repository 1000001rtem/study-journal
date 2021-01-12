package ru.eremin.studytableback.filter

import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import ru.eremin.studytableback.error.StudyJournalException
import ru.eremin.studytableback.security.service.AuthenticationService
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Фильтр, проверяющий токен авторизации в хедерах запроса
 */
class JwtTokenFilter(
    private val authenticationService: AuthenticationService
) : GenericFilterBean() {

    companion object {
        val log = LoggerFactory.getLogger(JwtTokenFilter::class.java)
    }

    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, chain: FilterChain) {
        val request: HttpServletRequest = servletRequest as HttpServletRequest
        val response: HttpServletResponse = servletResponse as HttpServletResponse

        try {
            authenticationService.authenticate(request)
        } catch (e: StudyJournalException) {
            SecurityContextHolder.clearContext()
            log.info("Auth error with token: ${request.getHeader("Authorization")}")
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
            return
        }

        chain.doFilter(request, servletResponse)
    }

}
