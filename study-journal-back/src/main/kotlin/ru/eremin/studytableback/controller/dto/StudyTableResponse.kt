package ru.eremin.studytableback.controller.dto

import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("status")
abstract class StudyJournalResponse(val status: Status)

class StudyJournalSuccessResponse : StudyJournalResponse(Status.SUCCESS)
class StudyJournalData<T>(val data: T) : StudyJournalResponse(Status.SUCCESS)
