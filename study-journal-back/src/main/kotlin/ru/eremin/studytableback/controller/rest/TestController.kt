package ru.eremin.studytableback.controller.rest

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @PostMapping(value = ["secure"])
    fun secure() = "Secure success"

}