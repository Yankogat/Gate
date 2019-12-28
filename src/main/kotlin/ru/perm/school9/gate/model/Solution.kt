package ru.perm.school9.gate.model

import ru.perm.school9.gate.model.enum.ESubmitSolutionCompiler

data class Solution (
        var sourceCode: String?,
        var compiler: ESubmitSolutionCompiler?
)