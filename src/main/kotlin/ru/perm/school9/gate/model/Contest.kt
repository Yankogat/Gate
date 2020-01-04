package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.perm.school9.gate.model.alias.IdList
import ru.perm.school9.gate.model.enum.EContestType

@Document(collection = "Contests")
data class Contest (
        @Id
        var id: String?,
        var problemIds: IdList,
        var userIds: IdList,
        var affectsUserRating: Boolean?,
        var contestType: EContestType?
)


