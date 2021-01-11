package ru.eremin.studytableback.dao.model

import java.time.Instant
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "teachers")
class Teacher(

    @Id
    @Column(name = "teacher_id")
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
    val birthday: Instant

) : AbstractTable()