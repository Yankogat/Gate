package ru.perm.school9.gate.model.monitor

data class MonitorProblemStanding (
        var problemId: String,
        var tryCount: Int,
        var score: Int?,
        var penalty: Int?
)
