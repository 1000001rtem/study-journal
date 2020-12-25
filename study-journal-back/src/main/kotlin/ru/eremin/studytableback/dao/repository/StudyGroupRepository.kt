package ru.eremin.studytableback.dao.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.eremin.studytableback.dao.model.StudyGroup

@Repository
interface StudyGroupRepository : JpaRepository<StudyGroup, String>