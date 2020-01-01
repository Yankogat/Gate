package ru.perm.school9.gate.model

data class MonitorTaskStanding (
        var taskId: String,
        var tryCount: Int,
        var points: Int,
        var penalty: Int?
)
