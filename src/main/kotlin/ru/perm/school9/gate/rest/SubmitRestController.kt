package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.perm.school9.gate.model.Submit
import ru.perm.school9.gate.service.*

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

    @Autowired
    private lateinit var authenticationService: AuthenticationService

    //TODO
    // get opinion on the length of the urls

    @GetMapping("/submits")
    fun getAllSubmits(): List<Submit> {
        //TODO
        // accessible by admins and higher

        return submitService.getAllSubmits()
    }
    @GetMapping("/submits/{submitId}")
    fun getSubmitById(@PathVariable submitId: String): Submit {
        val submit =  submitService.getSubmitById(submitId)
        //TODO
        // check if submit is authored by authenticated user unless it is admin

        return submit
    }

    @GetMapping("/contests/{contestId}/submits")
    fun getAllSubmitsByContest(@PathVariable contestId: String): List<Submit> {
        //TODO
        // accessible by admin and higher

        val contest = contestService.getContestById(contestId)

        return submitService.getAllSubmitsByContest(contest)
    }

    @GetMapping("/users/{userId}/submits")
    fun getAllSubmitsByUser(@PathVariable userId: String): List<Submit> {
        //TODO
        // user can access only get their own submits
        // fully accessible by admin and higher
        val user = userService.getUserById(userId)

        return submitService.getAllSubmitsByUser(user)
    }

    @GetMapping("/contests/{contestId}/problems/{problemId}/submits")
    fun getAllSubmitsByContestAndProblem(@PathVariable contestId: String, @PathVariable problemId: String): List<Submit> {
        return emptyList()
        //TODO
        // get authenticated user
        // if found user is not admin call getAllSubmitsByContestAndProblemAndUser
    }

    @GetMapping("/contests/{contestId}/users/{userId}/submits")
    fun getAllSubmitsByContestAndUser(@PathVariable contestId: String, @PathVariable userId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/problems/{problemId}/users/{userId}/submits")
    fun getAllSubmitsByContestAndProblemAndUser(@PathVariable contestId: String, @PathVariable problemId: String, @PathVariable userId: String): List<Submit> {
        //TODO
        val contest = contestService.getContestById(contestId)

        val problem = problemService.getProblemFromContestById(contest, problemId)

        val user = userService.getUserById(userId)

        return submitService.getAllSubmitsByContestAndProblemAndUser(contest, problem, user)
    }

    @PostMapping("/contests/{contestId}/problems/{problemId}/submits")
    fun addSubmitByContestAndProblem(@RequestBody submit: Submit, @PathVariable contestId: String, @PathVariable problemId: String) {
        //TODO
        // get authenticated user
        // call addSubmitByContestAndProblemAndUser
    }

    @PostMapping("/contests/{contestId}/problems/{problemId}/users/{userId}/submits")
    fun addSubmitByContestAndProblemAndUser(@RequestBody submit: Submit, @PathVariable contestId: String, @PathVariable problemId: String, @PathVariable userId: String) {
        //TODO
        val contest = contestService.getContestById(contestId)

        val problem = problemService.getProblemFromContestById(contest, problemId)

        val user = userService.getUserById(userId)

        submitService.addSubmitByContestAndProblemAndUser(submit, contest, problem, user)
    }
}