package ru.eremin.studytableback.controller.dto

import com.fasterxml.jackson.annotation.JsonProperty

enum class Status {
    @JsonProperty("success")
    SUCCESS,

    @JsonProperty("error")
    ERROR,

    @JsonProperty("warning")
    WARNING
}
