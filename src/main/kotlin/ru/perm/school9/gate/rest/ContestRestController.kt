package ru.perm.school9.gate.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.perm.school9.gate.model.Contest

@RestController
@RequestMapping("/contests")
class ContestRestController {
    @GetMapping("/")
    fun getContestList(): List<Contest> {
        // TODO
        // Get all available contests for authenticated user
        // Return them in the response
        return listOf()
    }
}