package ru.perm.school9.gate.model.runtime

data class MonitorProblemStanding (
        var problemId: String,
        var tryCount: Int,
        var score: Int?,
        var penalty: Int?
)
