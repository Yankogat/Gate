package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.perm.school9.gate.model.enum.EContestType

@Document(collection = "Contests")
data class Contest (
        @Id
        var id: String?,
        var name: String? = "",
        var problemIds: MutableList<String>? = mutableListOf(),
        var userIds: MutableList<String>? = mutableListOf(),
        var affectsUserRating: Boolean? = false,
        var contestType: EContestType? = EContestType.KIROV
)


