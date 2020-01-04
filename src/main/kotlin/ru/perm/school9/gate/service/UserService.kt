package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.UserNotFoundException
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.repo.mongodb.UserRepository

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var submitService: SubmitService

    @Autowired
    private lateinit var contestService: ContestService

    fun getUserById(userId: String): User = userRepository.findById(userId).orElseThrow { UserNotFoundException() }

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getAllUsersFromContest(contest: Contest): List<User> =
            userRepository.findAllById(contest.userIds).toList()

    fun deleteUser(user: User) {
        submitService.deleteSubmitsMadeByUser(user)
        contestService.deleteUserFromAllContests(user)
        userRepository.deleteById(user.id!!)
    }
}