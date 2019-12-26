package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Users")
data class User (
        @Id
        var id: String
)