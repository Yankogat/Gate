package ru.perm.school9.gate.model.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Problems")
data class ProblemDTO (
        @Id
        var id: String,

        var name: String,
        var wording: String
) : DTO