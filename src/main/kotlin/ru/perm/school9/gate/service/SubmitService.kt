package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.SubmitNotFoundException
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.Problem
import ru.perm.school9.gate.model.Submit
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.repo.mongodb.SubmitRepository

@Service
class SubmitService {
    @Autowired
    private lateinit var submitRepository: SubmitRepository

    @Autowired
    private lateinit var testerCommunicationService: TesterCommunicationService

    fun getAllSubmits(): List<Submit> = submitRepository.findAll()


    fun getAllSubmitsByContestAndProblemAndUser(contest: Contest, problem: Problem, user: User): List<Submit> =
            submitRepository.findByContestIdAndProblemIdAndUserId(contest.id!!, problem.id!!, user.id!!)


    fun getSubmitById(submitId: String): Submit = submitRepository.findById(submitId).orElseThrow { SubmitNotFoundException() }


    fun addSubmitByContestAndProblemAndUser(submit: Submit, contest: Contest, problem: Problem, user: User) {
        submit.apply {
            contestId = contest.id
            problemId = problem.id
            userId = user.id
        }
        submitRepository.insert(submit)
        testerCommunicationService.sendSubmitForTesting(submit)
    }

    fun getAllSubmitsByContest(contest: Contest): List<Submit> = submitRepository.findByContestId(contest.id!!)
}