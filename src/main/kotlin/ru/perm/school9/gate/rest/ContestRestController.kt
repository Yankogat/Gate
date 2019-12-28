package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.service.ContestService

@RestController
@RequestMapping("/contests")
class ContestRestController {
    @Autowired
    private lateinit var contestService: ContestService

    @GetMapping
    fun getContestList(): List<Contest> {
        //TODO
        // Get all available contests for authenticated user
        // Return them in the response
        return contestService.getAvailableContests()
    }

    @PostMapping
    fun addContest(contest: Contest) {
        //TODO
        // check if user is admin
        contestService.addContest(contest)
    }
}