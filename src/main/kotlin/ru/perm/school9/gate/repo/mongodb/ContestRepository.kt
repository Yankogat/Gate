package ru.perm.school9.gate.repo.mongodb

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import ru.perm.school9.gate.model.Contest

@Repository
interface ContestRepository : MongoRepository<Contest, String> {
    @Query("{userId: ?0}")
    fun findContestsByUserId(userId: String): List<Contest>
}