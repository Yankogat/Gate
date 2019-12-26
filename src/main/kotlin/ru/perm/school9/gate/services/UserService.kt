package ru.perm.school9.gate.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.repo.mongodb.UserRepository

@Service
class UserService {
    @Autowired
    private lateinit var userRepository: UserRepository

    fun getUserById(userId: String): User {
        //TODO
        // specify exception
        return userRepository.findById(userId).orElseThrow { Exception() }
    }
}