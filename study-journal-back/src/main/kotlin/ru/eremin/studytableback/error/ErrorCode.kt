package ru.eremin.studytableback.error

import ru.eremin.studytableback.error.ErrorType.AUTH
import ru.eremin.studytableback.error.ErrorType.BUSINESS

/**
 * Коды ошибок приложения. Может ковертироваться в исключение
 */
enum class ErrorCode : Error {

    INVALID_TOKEN(AUTH, "Invalid token: %s", "Ошибка авторизации"),
    EXPIRED_TOKEN(AUTH, "Expired token", "Разъединено. Просьба переавторизоваться"),
    USER_NOT_FOUND(AUTH, "User with email: %s not found", "Ошибка авторизации"),

    UNSUPPORTED(BUSINESS, "Unsupported yet", "Операция пока не поддерживается, попробуйте позже")
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