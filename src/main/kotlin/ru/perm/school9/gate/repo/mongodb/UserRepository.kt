package ru.perm.school9.gate.repo.mongodb

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import ru.perm.school9.gate.model.User

@Repository
interface UserRepository : MongoRepository<User, String> {
    @Query("{\"loginInfo.username\": ?0}")
    fun findByUsername(username: String): User?
    @Query("{email: ?0}")
    fun findByEmail(email: String): User?
}