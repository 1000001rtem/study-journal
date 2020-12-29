package ru.eremin.studytableback.error

import ru.eremin.studytableback.error.ErrorType.AUTH

/**
 * Коды ошибок приложения. Может ковертироваться в исключение
 */
enum class ErrorCode : Error {

    INVALID_TOKEN(AUTH, "Invalid token: %s", "Ошибка авторизации"),
    EXPIRED_TOKEN(AUTH, "Expired token", "Разъединено. Просьба переавторизоваться")
    ;

    override val type: ErrorType
    override val message: String
    override val displayMessage: String?

    constructor(type: ErrorType, message: String, displayMessage: String?) {
        this.type = type
        this.message = message
        this.displayMessage = displayMessage
    }
}