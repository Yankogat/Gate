package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Contests")
data class Contest (
        @Id
        var id: String,
        var problemIds: IdList,
        var userIds: IdList
)


