package ru.perm.school9.gate.model

data class MonitorStanding (
        var userId: String,
        var totalPoints: Int?,
        var totalPenalty: Int?,
        var position: Int?,
        var problemStandings: List<MonitorProblemStanding>
)
