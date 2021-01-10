package ru.eremin.studytableback.security.service

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.eremin.studytableback.dao.repository.StudentRepository
import ru.eremin.studytableback.dao.repository.TeacherRepository
import ru.eremin.studytableback.error.ErrorCode
import ru.eremin.studytableback.security.Role
import java.util.*

@Service
class UserService(
    @Qualifier(TeacherDetailService.NAME)
    val teacherDetailService: UserDetailsService,

    @Qualifier(StudentDetailService.NAME)
    val studentDetailService: UserDetailsService,

    private val teacherRepository: TeacherRepository,

    private val studentRepository: StudentRepository
) {

    fun getUserDetails(email: String?, role: Role): UserDetails =
        when (role) {
            Role.TEACHER -> teacherDetailService.loadUserByUsername(email)
            Role.STUDENT -> studentDetailService.loadUserByUsername(email)
            Role.ADMIN -> throw ErrorCode.UNSUPPORTED.asException() //TODO: исправить после добавления админки
        }

    fun getUserId(email: String?, role: Role): UUID? =
        when (role) {
            Role.TEACHER -> teacherRepository.findByEmail(email)?.id
            Role.STUDENT -> studentRepository.findByEmail(email)?.id
            Role.ADMIN -> throw ErrorCode.UNSUPPORTED.asException() //TODO: исправить после добавления админки
        }
}