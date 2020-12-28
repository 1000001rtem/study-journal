package ru.eremin.studytableback.dao.model

import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "lessons")
class Lesson(

    @Id
    @Column(name = "lesson_id")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "lesson_number")
    val number: Int,

    @ManyToOne
    @JoinColumn(name = "study_group")
    val studyGroup: StudyGroup,

    @ManyToOne
    @JoinColumn(name = "course_id")
    val course: Course,

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    val teacher: Teacher,

    @Column(name = "theme")
    val theme: String,

    @Column(name = "homework")
    val homework: String? = null,

    @Column(name = "lesson_date")
    val lessonDate: Instant,

    @Column(name = "note")
    val note: String? = null,

    @OneToMany
    val marks: List<Mark> = emptyList()

) : AbstractTable()