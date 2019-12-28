package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.service.ContestService
import ru.perm.school9.gate.service.UserService

@RestController
class UserRestController {
    @Autowired
    private lateinit var contestService: ContestService

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/contest/{contestId}/users")
    fun getUsersFromContest(@PathVariable contestId: String): List<User> {
        //TODO
        return emptyList()
    }

    @PostMapping("/contest/{contestId}/users")
    fun addUserContest(@RequestBody userId: String, @PathVariable contestId: String){
        //TODO
        // check if user is admin
        val contest = contestService.getAvailableContestById(contestId)

        val user = userService.getUserById(userId)

        contestService.addUserToContest(contest, user)
    }
}