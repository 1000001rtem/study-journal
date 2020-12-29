package ru.eremin.studytableback.error

import ru.eremin.studytableback.error.ErrorType.BUSINESS

/**
 * Коды ошибок приложения. Может ковертироваться в исключение
 */
enum class ErrorCode : Error {

    TEST_ERROR(type = BUSINESS, message = "Test error", displayMessage = "Тестовый код ошибки");

    override val type: ErrorType
    override val message: String
    override val displayMessage: String?

    constructor(type: ErrorType, message: String, displayMessage: String?) {
        this.type = type
        this.message = message
        this.displayMessage = displayMessage
    }
}