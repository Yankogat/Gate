package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.EmailHasBeenTakenException
import ru.perm.school9.gate.exception.UserNotFoundException
import ru.perm.school9.gate.exception.UsernameHasBeenTakenException
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.repo.mongodb.UserRepository

@Service
class UserService : UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var submitService: SubmitService

    @Autowired
    private lateinit var contestService: ContestService

    fun getUserById(userId: String): User = userRepository.findById(userId).orElseThrow { UserNotFoundException() }

    fun getAllUsers(): List<User> = userRepository.findAll()

    fun getAllUsersFromContest(contest: Contest): List<User> =
            userRepository.findAllById(contest.userIds.orEmpty()).toList()

    fun deleteUser(user: User) {
        submitService.deleteSubmitsMadeByUser(user)
        contestService.deleteUserFromAllContests(user)
        userRepository.deleteById(user.id!!)
    }

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username) ?: throw UserNotFoundException()

    fun addUser(user: User) {
        userRepository.insert(user)
    }

    fun checkIfUserCanBeRegistered(user: User) {
        if (userRepository.findByUsername(user.loginInfo!!.username!!) != null)
            throw UsernameHasBeenTakenException()
        if (userRepository.findByEmail(user.email!!) != null)
            throw EmailHasBeenTakenException()
    }
}