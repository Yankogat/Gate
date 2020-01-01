package ru.perm.school9.gate.model

data class MonitorProblemStanding (
        var problemId: String,
        var tryCount: Int,
        var score: Int,
        var penalty: Int?
)
