package ru.eremin.studytableback.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.eremin.studytableback.dao.model.Lesson
import ru.eremin.studytableback.dao.model.Mark
import ru.eremin.studytableback.dao.model.MarkPk
import ru.eremin.studytableback.dao.model.Student

@Repository
interface MarkRepository : JpaRepository<Mark, MarkPk> {

    fun findByIdStudent(student: Student): List<Mark>

    fun findByIdLesson(student: Student): List<Mark>

    fun findByIdStudentAndIdLesson(student: Student, lesson: Lesson): Mark
}