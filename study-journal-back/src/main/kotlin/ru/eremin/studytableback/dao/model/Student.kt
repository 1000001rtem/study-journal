package ru.eremin.studytableback.dao.model

import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "students")
class Student(

    @Id
    @Column(name = "student_id")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "first_name")
    val name: String,

    @Column(name = "surname")
    val surname: String,

    @Column(name = "patronymic")
    val patronymic: String? = null,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "birthday")
    val birthday: Instant,

    @ManyToOne
    @JoinColumn(name = "group_number")
    val group: StudyGroup? = null,

    @OneToMany
    val marks: List<Mark> = emptyList()

) : AbstractTable()