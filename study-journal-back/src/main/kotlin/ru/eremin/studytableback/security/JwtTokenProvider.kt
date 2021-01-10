package ru.eremin.studytableback.security

import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import ru.eremin.studytableback.error.ErrorCode.*
import java.security.Key
import java.util.*
import javax.servlet.http.HttpServletRequest

/**
 * Провайдер для работы с JWT токенами
 */
@Component
class JwtTokenProvider(
    private val secretKey: Key? = Keys.secretKeyFor(SignatureAlgorithm.HS256)
) {

    @Value("\${security.jwt.token.expire-length:3600000}")
    private val validityInMilliseconds: Long = 0

    /**
     * Создание токена
     *
     * @param username имя пользоватея
     * @param role роль пользователя
     * @return JWT токен
     */
    fun createToken(username: String, role: Role): String {
        val claims = Jwts.claims().setSubject(username)
        claims.put("role", role)
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(this.secretKey)
            .compact()
    }

    /**
     * Получение токена
     *
     * @param request http реквест
     * @return "чистый" токен без "Beaver"
     */
    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    /**
     * Получение логина пользователя из токена
     *
     * @param token
     * @return логин пользователя
     */
    fun getUserLogin(token: String?): String? =
        Jwts.parserBuilder().setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
            .subject

    /**
     * Валидация токена
     *
     * @param token
     * @throws StudyJournalException в случае если токен не прошёл проверку
     */
    fun validateToken(token: String?) {
        if (token.isNullOrBlank()) throw INVALID_TOKEN.formatMessage(token).asException()
        try {
            val claims: Jws<Claims> = Jwts.parserBuilder().setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)

            if (claims.body.expiration.before(Date())) {
                throw EXPIRED_TOKEN.asException()
            }
        } catch (e: JwtException) {
            throw INVALID_TOKEN.formatMessage(token).asException()
        } catch (e: IllegalArgumentException) {
            throw INVALID_TOKEN.formatMessage(token).asException()
        }
    }

    fun getUserRole(token: String?): Role =
        Jwts.parserBuilder().setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
            .get("role", Role::class.java)
}