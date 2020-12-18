package ru.eremin.studytableback.dao.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.Instant
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class AbstractTable {

    @CreatedBy
    @Column(name = "create_user", nullable = false)
    val createUser: String = "system"

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    val createTime: Instant = Instant.now()

    @LastModifiedBy
    @Column(name = "last_modify_user", nullable = false)
    val lastModifiedUser: String = "system"

    @LastModifiedDate
    @Column(name = "last_modify_time", nullable = false)
    val lastModifiedTime: Instant = Instant.now()
}