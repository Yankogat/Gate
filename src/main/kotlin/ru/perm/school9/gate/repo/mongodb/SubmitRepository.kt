package ru.perm.school9.gate.repo.mongodb

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.Submit

@Repository
interface SubmitRepository : MongoRepository<Submit, String> {
    @Query("{contestId: ?0")
    fun findByContestId(contestId: String): List<Submit>

    @Query("{contestId: ?0, userId: ?2}")
    fun findByContestIdAndUserId(contestId: String, userId: String): List<Submit>

    @Query("{contestId: ?0, problemId: ?1, userId: ?2}")
    fun findByContestIdAndProblemIdAndUserId(contestId: String, problemId: String, userId: String): List<Submit>

}