package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.ContestNotFoundException
import ru.perm.school9.gate.exception.UserNotFoundException
import ru.perm.school9.gate.model.*
import ru.perm.school9.gate.model.monitor.MonitorProblemStanding
import ru.perm.school9.gate.model.monitor.MonitorStanding
import ru.perm.school9.gate.repo.mongodb.ContestRepository

@Service
class ContestService {
    @Autowired
    private lateinit var contestRepository: ContestRepository

    @Autowired
    private lateinit var submitService: SubmitService

    fun getAllContests(): List<Contest> {
        return contestRepository.findAll()
    }

    fun getContestById(contestId: String): Contest {
        return contestRepository.findById(contestId).orElseThrow { ContestNotFoundException() }
    }

    fun addContest(contest: Contest) = contestRepository.insert(contest)

    private fun updateContest(contest: Contest) = contestRepository.save(contest)

    fun addProblemToContest(contest: Contest, problem: Problem) {
        contest.problemIds!!.add(problem.id!!)

        updateContest(contest)
    }

    fun addUserToContest(contest: Contest, user: User) {
        contest.userIds!!.add(user.id!!)

        updateContest(contest)
    }

    fun getMonitorByContest(contest: Contest): List<MonitorStanding> {
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

        var monitorStandings = contest.userIds?.map { userId ->

            MonitorStanding(userId, null, contest.problemIds?.map { problemId ->
                val filteredSubmits = filterSubmitsByProblemIdAndUserId(submits, problemId, userId)
                val bestSubmit = getSubmitWithHighestScore(filteredSubmits)
                        ?: Submit(null, null, null, null, null, null, null)

                // even if user has no submits on some problem, they should have score of 0
                MonitorProblemStanding(problemId, filteredSubmits.size, bestSubmit.summary?.score ?: 0, null)
            } ?: emptyList())

        }
        // make sure list is not null (can happen when contest has null instead of userIds field
        monitorStandings = monitorStandings ?: emptyList()

        // set place to each standing
        //TODO
        // this block depends on contest type
        // add
        monitorStandings = monitorStandings.sortedBy { standing ->
            standing.totalScore
        }.mapIndexed { index, standing ->
            standing.apply {
                // places go from 1, not from 0
                place = index + 1
            }
        }

        return monitorStandings
    }

    fun removeUserFromContest(contest: Contest, user: User) {
        contest.userIds?.find { it == user.id!! } ?: throw UserNotFoundException()

        contest.userIds?.remove(user.id!!)

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