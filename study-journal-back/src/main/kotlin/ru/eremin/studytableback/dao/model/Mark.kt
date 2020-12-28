package ru.eremin.studytableback.dao.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "marks")
class Mark(

    @EmbeddedId
    val id: MarkPk

) : AbstractTable()

@Embeddable
class MarkPk(

    @ManyToOne
    @JoinColumn(name = "student_id")
    val student: Student,

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    val lesson: Lesson

) : Serializable