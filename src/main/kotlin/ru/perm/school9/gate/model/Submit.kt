package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.perm.school9.gate.model.enum.ESubmitSolutionCompiler
import ru.perm.school9.gate.model.enum.ESubmitStatus

@Document(collection = "Submits")
data class Submit (
        @Id
        var id: String?,
        var contestId: String?,
        var problemId: String?,
        var userId: String?,
        var solution: Solution?,
        var status: ESubmitStatus? = ESubmitStatus.AWAITING_TESTING
)