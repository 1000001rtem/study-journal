package ru.eremin.studytableback.controller.dto

import ru.eremin.studytableback.security.Role
import java.util.*

data class AuthRequest(
    val login: String,
    val password: String,
    val role: Role
)

data class AuthResponse(
    val userId: UUID,
    val login: String,
    val token: String,
    val role: Role
)