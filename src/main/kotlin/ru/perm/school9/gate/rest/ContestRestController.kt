package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.runtime.MonitorStanding
import ru.perm.school9.gate.service.ContestService

@RestController
@RequestMapping("/contests")
class ContestRestController {
    @Autowired
    private lateinit var contestService: ContestService

    @GetMapping
    fun getContestList(): List<Contest> {
        return contestService.getAvailableContests()
    }

    @GetMapping("/{contestId}")
    fun getContestById(@PathVariable contestId: String): Contest {
        return contestService.getAvailableContestById(contestId)
    }

    @GetMapping("/{contestId}/monitor")
    fun getContestMonitor(@PathVariable contestId: String): List<MonitorStanding> {
        val contest = getContestById(contestId)
        return contestService.getMonitorByContest(contest)
    }

    @PostMapping
    fun addContest(@RequestBody contest: Contest) {
        //TODO
        // check if user is admin
        contestService.addContest(contest)
    }
}