package ru.eremin.studytableback.dao.model

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * Базовый набор полей для таблиц, автоматически заполняет
 * необходимые служебные поля. Использует AuditingEntityListener для отслеживания изменений.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractTable {

    @CreatedBy
    @Column(name = "create_user", nullable = false)
    var createUser: String = ""

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    var createTime: Instant = Instant.now()

    @LastModifiedBy
    @Column(name = "last_modify_user", nullable = false)
    var lastModifiedUser: String = ""

    @LastModifiedDate
    @Column(name = "last_modify_time", nullable = false)
    var lastModifiedTime: Instant = Instant.now()
}