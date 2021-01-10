package ru.eremin.studytableback.security.service

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.eremin.studytableback.dao.repository.StudentRepository
import ru.eremin.studytableback.error.ErrorCode.USER_NOT_FOUND
import ru.eremin.studytableback.security.Role

@Service(StudentDetailService.NAME)
class StudentDetailService(private val studentRepository: StudentRepository) : UserDetailsService {

    companion object {
        const val NAME = "studentDetailService"
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        val teacher = studentRepository.findByEmail(email) ?: throw USER_NOT_FOUND.formatMessage(email).asException()
        val builder = User.withUsername(email)
        builder.password(teacher.password)
        builder.roles(Role.STUDENT.name)
        return builder.build()
    }
}