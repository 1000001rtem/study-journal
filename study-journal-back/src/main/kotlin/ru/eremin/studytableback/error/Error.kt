package ru.eremin.studytableback.error

/**
 * Абстракция ошибки
 */
interface Error {
    val type: ErrorType
    val message: String
    val displayMessage: String?

    /**
     * Форматирование сообщения
     * Пример:
     * ```
     * ERROR_CODE(message: "error with id: %s", displayMessage: "display error")
     * ...
     * ERROR_CODE.formatMessage(id).asException()
     * ```
     *
     * @param values значения для форматирования
     * @return Error
     */
    fun formatMessage(vararg values: Any): Error =
        FormattedError(type, message.format(*values), displayMessage)

    /**
     * Форматирование отображаемого сообщения
     * Пример:
     * ```ERROR_CODE(message: "error", displayMessage: "display error with id: %s")
     * ...
     * ERROR_CODE.formatDisplayMessage(id).asException()```
     *
     * @param values значения для форматирования
     * @return Error
     */
    fun formatDisplayMessage(vararg values: Any): Error =
        FormattedError(type, message, displayMessage?.format(*values))

    /**
     * Форматирование обоих сообщений
     * Пример:
     * ```ERROR_CODE(message: "error with id: %s", displayMessage: "display error with id: %s")
     * ...
     * ERROR_CODE.formatBothMessages(id).asException()```
     *
     * @param values значения для форматирования
     * @return Error
     */
    fun formatBothMessages(vararg values: Any): Error =
        FormattedError(type, message.format(*values), displayMessage?.format(*values))

    /**
     * Конвертация ошибки в исключение
     *
     * @return исключение [StudyJournalException]
     */
    fun asException() = StudyJournalException(this)
}

/**
 * Вспомогательный класс для ошибки с форматированным(и) сообщением(ми).
 */
data class FormattedError(
    override val type: ErrorType,
    override val message: String,
    override val displayMessage: String?
) : Error
