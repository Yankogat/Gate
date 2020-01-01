package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.ContestNotFoundException
import ru.perm.school9.gate.model.*
import ru.perm.school9.gate.model.alias.IdList
import ru.perm.school9.gate.model.alias.Monitor
import ru.perm.school9.gate.repo.mongodb.ContestRepository

/** ContestService is responsible for constructing/deconstructing Contest objects from/to ContestDTOs
 *  and basic contest related tasks as middleman between end-user and repository
 * */
@Service
class ContestService {
    @Autowired
    private lateinit var contestRepository: ContestRepository

    @Autowired
    private lateinit var submitService: SubmitService

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
        contest.userIds = contest.userIds ?: IdList()
        contest.userIds!!.add(user.id!!)

        updateContest(contest)
    }

    fun getMonitorByContest(contest: Contest): Monitor {
        //TODO
        // test
        // rewrite
        val submits = submitService.getAllSubmitsByContest(contest)

        val filterSubmitsByProblemIdAndUserId = { submits: List<Submit>, problemId: String, userId: String ->
            submits.filter { submit ->
                submit.userId!! == userId
                        && submit.problemId == problemId
            }
        }

        val getSubmitWithHighestScore: (submits: List<Submit>) -> Submit? = {
            submits.sortedBy { submit ->
                submit.summary?.score
            }.firstOrNull()
        }

        val monitor = contest.userIds?.map { userId ->
            MonitorStanding(userId, null, null, null, contest.problemIds?.map { problemId ->
                val filteredSubmits = filterSubmitsByProblemIdAndUserId(submits, problemId, userId)
                val bestSubmit = getSubmitWithHighestScore(filteredSubmits)
                        ?: Submit(null, null, null, null, null, summary = SubmitSummary(0))
                MonitorProblemStanding(problemId, filteredSubmits.size, bestSubmit.summary?.score ?: 0, null)
            } ?: emptyList())
        }

        return monitor as Monitor
    }
}