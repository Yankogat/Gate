package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.perm.school9.gate.model.Problem
import ru.perm.school9.gate.service.ContestService
import ru.perm.school9.gate.service.ProblemService

@RestController
class ProblemRestController {
    @Autowired
    private lateinit var contestService: ContestService

    @Autowired
    private lateinit var problemService: ProblemService

    @GetMapping("/contests/{contestId}/problems")
    fun getProblems(@PathVariable contestId: String): List<Problem> {
        //TODO
        // Get contest by id if it is available for authenticated user
        // If not, throw 403
        val contest = contestService.getAvailableContestById(contestId)
        // Get all problems from specified contest
        // Return said problems
        return problemService.getAllProblemsFromContest(contest)
    }

    @PostMapping("/contests/{contestId}/problems")
    fun addProblemToContest(@RequestBody problemId: String,  @PathVariable contestId: String) {
        //TODO
        // check if user is admin
        val contest = contestService.getAvailableContestById(contestId)

        val problem = problemService.getProblemById(problemId)

        contestService.addProblemToContest(contest, problem)
    }

    @GetMapping("/contests/{contestId}/problems/{problemId}")
    fun getProblemFromContest(@PathVariable contestId: String, @PathVariable problemId: String): Problem {
        // TODO
        // Get contest by id if it is available for authenticated user
        // If not, throw 403
        val contest = contestService.getAvailableContestById(contestId)
        // Get problem by it from specified contest
        // Return said problem
        return problemService.getProblemFromContestById(contest, problemId)
    }

    @GetMapping("/problems/{problemId}")
    fun getProblem(@PathVariable problemId: String): Problem {
        // TODO
        // Get problem out of all problems in the database by id
        // Return said problem
        return problemService.getProblemById(problemId)
    }
}