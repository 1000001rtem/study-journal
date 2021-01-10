package ru.eremin.studytableback.security.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.eremin.studytableback.dao.repository.TeacherRepository
import ru.eremin.studytableback.error.ErrorCode.USER_NOT_FOUND
import ru.eremin.studytableback.security.Role

@Service(TeacherDetailService.NAME)
class TeacherDetailService(private val teacherRepository: TeacherRepository) : UserDetailsService {

    companion object {
        const val NAME = "teacherDetailService"
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        val teacher = teacherRepository.findByEmail(email) ?: throw USER_NOT_FOUND.formatMessage(email).asException()
        val builder = User.withUsername(email)
        builder.password(teacher.password)
        builder.roles(Role.TEACHER.name)
        return builder.build()
    }
}