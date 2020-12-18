package ru.eremin.studytableback.configuration

import org.springframework.data.domain.AuditorAware
import java.util.*

/**
 * Аудитор, который определяет пользователя (для стандартных полей create_user и last_modify_user
 */
class UserAuditorAware : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> = Optional.ofNullable("system") //todo: убрать хардкод после внедрения авторизации
}