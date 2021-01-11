package ru.eremin.studytableback.controller.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.eremin.studytableback.controller.dto.StudyJournalSuccessResponse
import ru.eremin.studytableback.controller.dto.UserDto
import ru.eremin.studytableback.security.service.UserService

@RestController
class RegistrationController(
    private val userService: UserService
) {

    @PostMapping("/registration")
    fun registration(@RequestBody request: UserDto): StudyJournalSuccessResponse {
        userService.createUser(request)
        return StudyJournalSuccessResponse()
    }
}