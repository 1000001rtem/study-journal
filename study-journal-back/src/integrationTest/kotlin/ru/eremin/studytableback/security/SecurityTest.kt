package ru.eremin.studytableback.security

import com.jayway.jsonpath.JsonPath
import org.json.JSONObject
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.transaction.annotation.Transactional
import ru.eremin.studytableback.controller.dto.UserDto
import ru.eremin.studytableback.security.service.UserService
import ru.eremin.studytableback.util.getRequestJson
import java.time.Instant

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
internal class SecurityTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    companion object {
        val log = LoggerFactory.getLogger(SecurityTest::class.java)
    }

    @Test
    fun `should return unauthorized code`() {
        mvc.perform(get("/v1/group")).andExpect(status().isUnauthorized)
        mvc.perform(post("/")).andExpect(status().isUnauthorized)
    }

    @Test
    fun `should success authorize`() {
        val user = UserDto(
            "Eric",
            Role.STUDENT,
            "Cartman",
            "no",
            "e.cartman@park.com",
            passwordEncoder.encode("pass"),
            Instant.now()
        )

        userService.createUser(user)
        val authRequest = getRequestJson("/request/auth/auth_success_request.json")
        var token = ""
        mvc.perform(
            post("/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authRequest)
        )
            .andDo {
                log.info("REQUEST: ${JSONObject(it.request.contentAsString).toString(4)}")
                log.info("RESPONSE: ${JSONObject(it.response.contentAsString).toString(4)}")
            }
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value("success"))
            .andExpect(jsonPath("$.data.userId").isNotEmpty)
            .andExpect(jsonPath("$.data.login").value("e.cartman@park.com"))
            .andExpect(jsonPath("$.data.token").isNotEmpty)
            .andExpect(jsonPath("$.data.role").value("student"))
            .andDo { token = JsonPath.parse(it.response.contentAsString).read("$.data.token") }

        mvc.perform(get("/v1/group").header("Authorization", "Bearer $token")).andExpect(status().isOk)
    }

    @Test
    fun `should register new user`() {
        val registrationRequest = getRequestJson("/request/auth/registration_success_request.json")
        mvc.perform(
            post("/v1/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registrationRequest)
        )
            .andExpect(status().isOk)
            .andDo {
                log.info("REGISTRATION REQUEST: ${JSONObject(it.request.contentAsString).toString(4)}")
                log.info("REGISTRATION RESPONSE: ${JSONObject(it.response.contentAsString).toString(4)}")
            }
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value("success"))

        val userId = userService.getUserId("e.cartman@park.com", Role.STUDENT).toString()
        assertNotNull(userId)

        val authRequest = getRequestJson("/request/auth/auth_success_request.json")
        mvc.perform(
            post("/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authRequest)
        )
            .andDo {
                log.info("AUTH REQUEST: ${JSONObject(it.request.contentAsString).toString(4)}")
                log.info("AUTH RESPONSE: ${JSONObject(it.response.contentAsString).toString(4)}")
            }
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.status").value("success"))
            .andExpect(jsonPath("$.data.userId").value(userId))
            .andExpect(jsonPath("$.data.login").value("e.cartman@park.com"))
            .andExpect(jsonPath("$.data.token").isNotEmpty)
            .andExpect(jsonPath("$.data.role").value("student"))
    }

    @Test
    fun `should return unauthorized if token not valid`() {
        val badToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0Iiwicm9sZSI6InRlYWNoZXIiLCJpYXQiOjE2MTA0NjY0OTYsImV4cCI6MTYxMDQ2NzA5Nn0.sNgLbwIGhx8RF60DcNZ-p6uqObdhxtlOyqDKu0zVtq4"
        mvc.perform(
            get("/v1/group")
                .header("Authorization", "Bearer $badToken")
        ).andExpect(status().isUnauthorized)
    }
}