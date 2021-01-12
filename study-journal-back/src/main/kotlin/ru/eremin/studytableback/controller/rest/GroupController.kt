package ru.eremin.studytableback.controller.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class GroupController {

    @GetMapping("/group")
    fun findAll() = "Список групп" // TODO: затычка
}