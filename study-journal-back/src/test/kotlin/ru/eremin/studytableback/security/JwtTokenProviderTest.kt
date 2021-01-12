package ru.eremin.studytableback.security

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.mock.web.MockHttpServletRequest

internal class JwtTokenProviderTest {

    private val subj = JwtTokenProvider()

    @Test
    fun `should resolve token`() {
        val token =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0Iiwicm9sZSI6InRlYWNoZXIiLCJpYXQiOjE2MTA0NjY0OTYsImV4cCI6MTYxMDQ2NzA5Nn0.sNgLbwIGhx8RF60DcNZ-p6uqObdhxtlOyqDKu0zVtq4"
        val request = MockHttpServletRequest()
        request.addHeader("Authorization", "Bearer $token")
        val result = subj.resolveToken(request)
        assertEquals(token, result)
    }

    @Test
    fun `should create and validate token`() {
        val result = subj.createToken("test@test.ru", Role.TEACHER)
        assertDoesNotThrow { subj.validateToken(result) }
    }

    @Test
    fun `should return role and login`() {
        val token = subj.createToken("test@test.ru", Role.TEACHER)

        val username = subj.getUserLogin(token)
        val role = subj.getUserRole(token)

        assertEquals("test@test.ru", username)
        assertEquals(Role.TEACHER, role)
    }
}