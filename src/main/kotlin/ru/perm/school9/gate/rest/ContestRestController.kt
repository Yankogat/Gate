package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.Problem
import ru.perm.school9.gate.services.ContestService
import ru.perm.school9.gate.services.ProblemService

@RestController
@RequestMapping("/contests")
class ContestRestController {
    @Autowired
    private lateinit var contestService: ContestService

    @Autowired
    private lateinit var problemService: ProblemService

    @GetMapping("/")
    fun getContestList(): List<Contest> {
        // TODO
        // Get all available contests for authenticated user
        // Return them in the response
        return contestService.getAvailableContests()
    }

    @GetMapping("{contestId}/problems/")
    fun getProblems(@PathVariable contestId: String): List<Problem> {
        // TODO
        // Get contest by id if it is available for authenticated user
        // If not, throw 403
        val contest = contestService.getAvailableContestById(contestId)
        // Get all problems from specified contest
        // Return said problems
        return problemService.getAllProblemsFromContest(contest)
    }
}