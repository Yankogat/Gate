package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.perm.school9.gate.model.Submit
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.services.ContestService
import ru.perm.school9.gate.services.ProblemService
import ru.perm.school9.gate.services.SubmitService
import ru.perm.school9.gate.services.UserService

@RestController
class SubmitRestController {
    @Autowired
    private lateinit var contestService: ContestService

    @Autowired
    private lateinit var problemService: ProblemService

    @Autowired
    private lateinit var submitService: SubmitService

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/submits/")
    fun getAllSubmits(): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/submits/")
    fun getAllSubmitsByContest(@PathVariable contestId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/users/{userId}/submits/")
    fun getAllSubmitsByUser(@PathVariable userId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/problems/{problemId}/submits/")
    fun getAllSubmitsByContestAndProblem(@PathVariable contestId: String, @PathVariable problemId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/users/{userId}/submits/")
    fun getAllSubmitsByContestAndUser(@PathVariable contestId: String, @PathVariable userId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/problems/{problemId}/users/{userId}/submits/")
    fun getAllSubmitsByContestAndProblemAndUser(@PathVariable contestId: String, @PathVariable problemId: String, @PathVariable userId: String): List<Submit> {
        //TODO
        val contest = contestService.getAvailableContestById(contestId)

        val problem = problemService.getProblemFromContestById(contest, problemId)

        val user = userService.getUserById(userId)

        return submitService.getAllSubmitsByContestAndProblemAndUser(contest, problem, user)
    }
}