package ru.eremin.studytableback.configuration

import org.springframework.data.domain.AuditorAware
import java.util.*

class UserAuditorAware : AuditorAware<String> {

    override fun getCurrentAuditor(): Optional<String> = Optional.ofNullable("system") //todo: убрать хардкод после внедрения авторизации

}