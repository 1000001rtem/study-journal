package ru.eremin.studytableback.error

/**
 * Типы ошибок приложения
 */
enum class ErrorType {

    /**
     * Ошибки авторизации
     */
    AUTH,

    /**
     * Ошибки связанные с бизнес-логикой
     */
    BUSINESS,

    /**
     * Системные ошибки
     */
    SYSTEM
}