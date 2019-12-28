package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.ContestNotFoundException
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.IdList
import ru.perm.school9.gate.model.Problem
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.repo.mongodb.ContestRepository
import java.lang.Exception

/** ContestService is responsible for constructing/deconstructing Contest objects from/to ContestDTOs
 *  and basic contest related tasks as middleman between end-user and repository
 * */
@Service
class ContestService {
    @Autowired
    private lateinit var contestRepository: ContestRepository

    fun getAvailableContests(): List<Contest> {
        //TODO
        // check contests for availability
        return contestRepository.findAll()
    }

    fun getAvailableContestById(contestId: String): Contest {
        //TODO
        // check contest for availability
        return contestRepository.findById(contestId).orElseThrow { ContestNotFoundException() }
    }

    fun addContest(contest: Contest) = contestRepository.insert(contest)

    private fun updateContest(contest: Contest) = contestRepository.save(contest)

    fun addProblemToContest(contest: Contest, problem: Problem) {
        //call this function only with problems acquired from database to ensure that they have id
        //contest acquired from database may not have problemId list
        contest.problemIds = contest.problemIds.orEmpty() as IdList
        contest.problemIds!!.add(problem.id!!)

        updateContest(contest)
    }

    fun addUserToContest(contest: Contest, user: User) {
        //contest acquired from database may not have problemId list
        contest.userIds = contest.userIds.orEmpty() as IdList
        contest.userIds!!.add(user.id!!)

        updateContest(contest)
    }
}