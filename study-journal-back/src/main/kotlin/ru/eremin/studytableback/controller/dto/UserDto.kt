package ru.eremin.studytableback.controller.dto

import ru.eremin.studytableback.dao.model.Student
import ru.eremin.studytableback.dao.model.StudyGroup
import ru.eremin.studytableback.dao.model.Teacher
import ru.eremin.studytableback.security.Role
import java.time.Instant

data class UserDto(
    val name: String,
    val role: Role,
    val surname: String,
    val patronymic: String? = null,
    val email: String,
    val password: String,
    val birthday: Instant,
    val group: StudyGroup? = null,
) {

    /**
     * Конвертация в сущность [Teacher]
     * @return [Teacher]
     */
    fun toTeacher() = Teacher(
        name = name,
        surname = surname,
        patronymic = patronymic,
        email = email,
        password = password,
        birthday = birthday
    )

    /**
     * Конвертация в сущность [Student]
     * @return [Student]
     */
    fun toStudent() = Student(
        name = name,
        surname = surname,
        patronymic = patronymic,
        email = email,
        password = password,
        birthday = birthday,
        group = group
    )
}