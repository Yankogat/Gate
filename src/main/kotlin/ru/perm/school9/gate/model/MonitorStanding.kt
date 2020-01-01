package ru.perm.school9.gate.model

data class MonitorStanding (
        var userId: String,
        var totalPoints: Int,
        var totalPenalty: Int?,
        var place: Int,
        var taskStandings: List<MonitorTaskStanding>
)
