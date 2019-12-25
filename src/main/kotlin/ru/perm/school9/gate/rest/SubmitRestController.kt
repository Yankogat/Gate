package ru.perm.school9.gate.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import ru.perm.school9.gate.model.Submit

@RestController
class SubmitRestController {
    @GetMapping("/submits/")
    fun getAllSubmits(): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/submits/")
    fun getAllSubmitsFromContest(@PathVariable contestId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/user/{userId}/submits/")
    fun getAllSubmitsFromUser(@PathVariable userId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/problems/{problemId}/submits/")
    fun getAllSubmitsFromContestAndProblem(@PathVariable contestId: String, @PathVariable problemId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/user/{userId}/submits/")
    fun getAllSubmitsContestAndUser(@PathVariable contestId: String, @PathVariable userId: String): List<Submit> {
        //TODO
        return emptyList()
    }

    @GetMapping("/contests/{contestId}/problems/{problemId}/user/{userId}/submits/")
    fun getAllSubmitsFromContestAndProblemAndUser(@PathVariable contestId: String, @PathVariable problemId: String, @PathVariable userId: String): List<Submit> {
        //TODO
        return emptyList()
    }
}