package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.monitor.MonitorStanding
import ru.perm.school9.gate.service.AuthenticationService
import ru.perm.school9.gate.service.ContestService

@RestController
@RequestMapping("/contests")
class ContestRestController {
    @Autowired
    private lateinit var contestService: ContestService

    @Autowired
    private lateinit var authenticationService: AuthenticationService

    @GetMapping
    fun getContestList(): List<Contest> {
        return contestService.getAllContests()
    }

    @GetMapping("/{contestId}")
    fun getContestById(@PathVariable contestId: String): Contest {
        return contestService.getContestById(contestId)
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