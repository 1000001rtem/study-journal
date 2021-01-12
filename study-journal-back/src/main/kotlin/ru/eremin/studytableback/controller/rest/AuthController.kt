package ru.eremin.studytableback.controller.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.eremin.studytableback.controller.dto.AuthRequest
import ru.eremin.studytableback.controller.dto.StudyJournalData
import ru.eremin.studytableback.controller.dto.StudyJournalResponse
import ru.eremin.studytableback.security.service.AuthenticationService

@RestController
@RequestMapping("/v1")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping(value = ["/auth"])
    fun auth(@RequestBody authRequest: AuthRequest): StudyJournalResponse =
        StudyJournalData(
            data = authenticationService.authenticate(authRequest)
        )
}