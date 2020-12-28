package ru.eremin.studytableback.dao.model

import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "courses")
class Course(

    @Id
    @Column(name = "course_id")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "course_name")
    val name: String,

    @Column(name = "description")
    val description: String? = null,

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    val teacher: Teacher,

    @ManyToOne
    @JoinColumn(name = "study_group")
    val studyGroup: StudyGroup,

    @Column(name = "active")
    val active: Boolean = false,

    @Column(name = "start_date")
    val startDate: Instant? = null,

    @Column(name = "end_date")
    val endDate: Instant? = null,

    @OneToMany
    val lessons: List<Lesson> = emptyList()

) : AbstractTable()