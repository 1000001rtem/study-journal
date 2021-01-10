package ru.eremin.studytableback.controller.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.eremin.studytableback.controller.dto.AuthRequest
import ru.eremin.studytableback.controller.dto.AuthResponse
import ru.eremin.studytableback.security.service.AuthenticationService

@RestController
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping(value = ["/auth"])
    fun auth(@RequestBody authRequest: AuthRequest): AuthResponse = authenticationService.authenticate(authRequest)
}