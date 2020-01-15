package ru.perm.school9.gate.model.monitor

data class MonitorStanding (
        var userId: String,
        var place: Int?,
        var problemStandings: List<MonitorProblemStanding>
) {
    val totalScore: Int
        get() = this.problemStandings.sumBy { it.score ?: 0 }
    val totalPenalty: Int
        get() = this.problemStandings.sumBy { it.penalty ?: 0 }
}
