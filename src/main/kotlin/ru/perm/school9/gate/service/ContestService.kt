package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.ContestNotFoundException
import ru.perm.school9.gate.exception.UserNotFoundException
import ru.perm.school9.gate.model.*
import ru.perm.school9.gate.model.alias.IdList
import ru.perm.school9.gate.model.alias.Monitor
import ru.perm.school9.gate.model.runtime.MonitorProblemStanding
import ru.perm.school9.gate.model.runtime.MonitorStanding
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
        contest.problemIds.add(problem.id!!)

        updateContest(contest)
    }

    fun addUserToContest(contest: Contest, user: User) {
        contest.userIds.add(user.id!!)

        updateContest(contest)
    }

    fun getMonitorByContest(contest: Contest): Monitor {
        // HELP, I AM DROWNING IN NULL SAFETY
        //TODO
        // decide if all those nullable properties should really be nullable

        val submits = submitService.getAllSubmitsByContest(contest)

        val filterSubmitsByProblemIdAndUserId = { submits: List<Submit>, problemId: String, userId: String ->
            submits.filter { submit ->
                submit.userId!! == userId
                        && submit.problemId == problemId
            }
        }

        val getSubmitWithHighestScore: (submits: List<Submit>) -> Submit? = {
            submits.minBy { submit ->
                submit.summary?.score ?: 0
            }
        }

        var monitorStandings = contest.userIds.map { userId ->

            MonitorStanding(userId, null, contest.problemIds.map { problemId ->
                val filteredSubmits = filterSubmitsByProblemIdAndUserId(submits, problemId, userId)
                val bestSubmit = getSubmitWithHighestScore(filteredSubmits)
                        ?: Submit(null, null, null, null, null, null, null)

                // even if user has no submits on some problem, they should have score of 0
                MonitorProblemStanding(problemId, filteredSubmits.size, bestSubmit.summary?.score
                        ?: 0, null)
            })

        }
        // make sure list is not null (can happen when contest has null instead of userIds field
        monitorStandings = monitorStandings ?: emptyList()

        // set place to each standing
        //TODO
        // this is block is dependent on contest type
        // move to where it should belong
        monitorStandings = monitorStandings.sortedBy { standing ->
            standing.totalScore
        }.mapIndexed { index, standing ->
            standing.apply {
                // places should start at 1, not at 0
                place = index + 1
            }
        }

        return Monitor().apply {
            addAll(monitorStandings)
        }
    }

    fun removeUserFromContest(contest: Contest, user: User) {
        contest.userIds.find { it == user.id!! } ?: throw UserNotFoundException()

        contest.userIds.remove(user.id!!)

        updateContest(contest)
    }

    fun getContestsByUser(user: User): List<Contest> = contestRepository.findContestsByUserId(user.id!!)

    fun deleteUserFromAllContests(user: User) {
        val contests = getContestsByUser(user)

        contests.forEach {contest ->
            removeUserFromContest(contest, user)
        }
    }
}