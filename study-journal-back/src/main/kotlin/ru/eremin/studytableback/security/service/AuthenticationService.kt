package ru.eremin.studytableback.security.service

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import ru.eremin.studytableback.controller.dto.AuthRequest
import ru.eremin.studytableback.controller.dto.AuthResponse
import ru.eremin.studytableback.error.ErrorCode
import ru.eremin.studytableback.security.JwtTokenProvider
import javax.servlet.http.HttpServletRequest

@Service
class AuthenticationService(
    private val userService: UserService,
    private val tokenProvider: JwtTokenProvider,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    fun authenticate(request: HttpServletRequest) {
        val token = tokenProvider.resolveToken(request)

        tokenProvider.validateToken(token)

        val login = tokenProvider.getUserLogin(token)
        val role = tokenProvider.getUserRole(token)
        val userDetail = userService.getUserDetails(login, role)

        val authToken = UsernamePasswordAuthenticationToken(userDetail.username, null, userDetail.authorities)
        SecurityContextHolder.getContext().authentication = authToken
    }

    fun authenticate(request: AuthRequest): AuthResponse {
        val userDetail = userService.getUserDetails(request.login, request.role)
        if (passwordEncoder.matches(request.password, userDetail.password)) {
            val authToken = UsernamePasswordAuthenticationToken(userDetail.username, null, userDetail.authorities)
            SecurityContextHolder.getContext().authentication = authToken

            val userId = userService.getUserId(request.login, request.role)
                ?: throw ErrorCode.USER_NOT_FOUND.formatMessage(
                    request.login
                ).asException()

            val token = tokenProvider.createToken(request.login, request.role)
            return AuthResponse(userId, userDetail.username, token, request.role)
        } else {
            SecurityContextHolder.clearContext()
            throw BadCredentialsException("Authentication failed")
        }
    }
}