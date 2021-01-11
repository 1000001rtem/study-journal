package ru.eremin.studytableback.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.eremin.studytableback.dao.model.Student
import java.util.*

@Repository
interface StudentRepository : JpaRepository<Student, UUID> {
    fun findByEmail(email: String?): Student?
}