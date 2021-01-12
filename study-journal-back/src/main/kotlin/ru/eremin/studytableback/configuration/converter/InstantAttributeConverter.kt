package ru.eremin.studytableback.configuration.converter

import java.time.Instant
import javax.persistence.AttributeConverter
import javax.persistence.Converter

/**
 * Конвертер для java типа Instant.
 * Преобразует Instant в Long и обратно.
 */
@Converter(autoApply = true)
class InstantAttributeConverter : AttributeConverter<Instant?, Long?> {

    override fun convertToDatabaseColumn(attribute: Instant?): Long? = attribute?.toEpochMilli()

    override fun convertToEntityAttribute(value: Long?): Instant = Instant.ofEpochMilli(value!!)
}