package ru.perm.school9.gate.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.service.AuthenticationService
import ru.perm.school9.gate.service.ContestService
import ru.perm.school9.gate.service.UserService

@RestController
class UserRestController {
    @Autowired
    private lateinit var contestService: ContestService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var authenticationService: AuthenticationService

    @GetMapping("/contest/{contestId}/users")
    fun getUsersFromContest(@PathVariable contestId: String): List<User> {
        //TODO
        // accessible by and admin and higher
        val contest = contestService.getContestById(contestId)

        return userService.getAllUsersFromContest(contest)
    }

    @PostMapping("/contest/{contestId}/users")
    fun addUserContest(@RequestBody userId: String, @PathVariable contestId: String){
        //TODO
        // accessible by admin and higher
        val contest = contestService.getContestById(contestId)

        val user = userService.getUserById(userId)

        contestService.addUserToContest(contest, user)
    }

    @GetMapping("/users/{userId}")
    fun getUserById(@PathVariable userId: String): User {
        //TODO
        // if accessed by regular user, get only partial info

        return userService.getUserById(userId)
    }

    @GetMapping("/users")
    fun getAllUsers() : List<User> {
        //TODO
        // accessible by admin and higher
        return userService.getAllUsers()
    }

    @PostMapping("/users")
    fun registerNewUser(@RequestBody user: User) {
        user.validateAndPrepareForRegistration()
        userService.checkIfUserCanBeRegistered(user)

        user.loginInfo!!.password = passwordEncoder.encode(user.password)

        userService.addUser(user)
    }

    @PostMapping("/users/{userId}/makeAdmin")
    fun makeUserAdmin(@PathVariable userId: String) {
        //TODO
        // accessible only by root
    }

    @DeleteMapping("/contest/{contestId}/users/{userId}")
    fun removeUserFromContest(@PathVariable contestId: String, @PathVariable userId: String) {
        //TODO
        // come up with something about which users can access this path

        val contest = contestService.getContestById(contestId)

        val user = userService.getUserById(userId)

        contestService.removeUserFromContest(contest, user)
    }

    @DeleteMapping("/users/{userId}")
    fun deleteUser(@PathVariable userId: String) {
        //TODO
        // come up with something about which users can access this path

        val user = userService.getUserById(userId)

        userService.deleteUser(user)
    }
}