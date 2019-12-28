package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Problems")
data class Problem (
        @Id
        var id: String?,
        var name: String?,
        var wording: String?
        //TODO
        // finally decide how tests are stored within the problem and implement them
)