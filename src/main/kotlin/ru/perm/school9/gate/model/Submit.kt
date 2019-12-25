package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import ru.perm.school9.gate.model.enum.SubmitSolutionCompiler
import ru.perm.school9.gate.model.enum.SubmitStatus

@Document(collection = "Submits")
data class Submit (
        @Id
        var id: String,
        var contestId: String,
        var problemId: String,
        var userId: String,
        var solutionSourceCode: String,
        var solutionCompiler: SubmitSolutionCompiler,
        var status: SubmitStatus = SubmitStatus.AWAITING_TESTING
)