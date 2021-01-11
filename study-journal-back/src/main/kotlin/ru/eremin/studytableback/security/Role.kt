package ru.eremin.studytableback.security

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Роли пользователя
 */
enum class Role {

    @JsonProperty("teacher")
    TEACHER,

    @JsonProperty("student")
    STUDENT,

    @JsonProperty("admin")
    ADMIN
}