package ru.eremin.studytableback.error

class StudyJournalException(error: Error) : RuntimeException("${error.type}: ${error.message}")