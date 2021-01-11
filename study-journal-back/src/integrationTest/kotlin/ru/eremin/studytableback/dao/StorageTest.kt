package ru.eremin.studytableback.dao

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import ru.eremin.studytableback.dao.model.*
import ru.eremin.studytableback.dao.repository.*
import java.time.Instant

@DataJpaTest
@ActiveProfiles("test")
class StorageTest {

    @Autowired
    private lateinit var courseRepository: CourseRepository

    @Autowired
    private lateinit var lessonRepository: LessonRepository

    @Autowired
    private lateinit var markRepository: MarkRepository

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @Autowired
    private lateinit var studyGroupRepository: StudyGroupRepository

    @Autowired
    private lateinit var teacherRepository: TeacherRepository

    @Test
    fun `should find all without errors`() {
        courseRepository.findAll()
        lessonRepository.findAll()
        markRepository.findAll()
        studentRepository.findAll()
        studyGroupRepository.findAll()
        teacherRepository.findAll()
    }

    @Test
    fun `should get list by association`() {
        val teacher = Teacher(
            name = "test",
            surname = "test",
            patronymic = "test",
            email = "test",
            password = "test",
            birthday = Instant.now()
        )

        teacherRepository.save(teacher)

        val studyGroup = StudyGroup(
            groupNumber = "test-001"
        )

        studyGroupRepository.save(studyGroup)

        val course  =Course(
            name = "testCourse",
            teacher = teacher,
            studyGroup = studyGroup
        )

        courseRepository.save(course)

        val lesson = Lesson(
            number = 1,
            studyGroup = studyGroup,
            course = course,
            teacher = teacher,
            theme = "testLesson",
            lessonDate = Instant.now()
        )

        lessonRepository.save(lesson)

        val student = Student(
            name = "test",
            surname = "test",
            patronymic = "test",
            email = "test",
            password = "test",
            group = studyGroup,
            birthday = Instant.now()
        )

        studentRepository.save(student)

        val mark = Mark(MarkPk(student, lesson))

        markRepository.save(mark)

        val result = markRepository.findByIdStudentAndIdLesson(student, lesson)

        assertEquals(student.id, result.id.student.id)
        assertEquals(lesson.id, result.id.lesson.id)
        assertNull(teacherRepository.findByEmail(null))
    }
}