package ru.eremin.studytableback.dao.model

import javax.persistence.*

@Entity
@Table(name = "study_groups")
class StudyGroup(

    @Id
    @Column(name = "group_number")
    val groupNumber: String,

    @OneToMany
    val students: List<Student> = emptyList(),

    @OneToMany
    val courses: List<Course> = emptyList()

) : AbstractTable()