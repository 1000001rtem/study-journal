package ru.eremin.studytableback.util

import org.springframework.core.io.ClassPathResource

fun getRequestJson(path: String) = ClassPathResource(path).inputStream.bufferedReader().use { it.readText() }
