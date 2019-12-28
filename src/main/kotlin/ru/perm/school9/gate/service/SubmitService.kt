package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.SubmitNotFoundException
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.Problem
import ru.perm.school9.gate.model.Submit
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.model.enum.SubmitSolutionCompiler
import ru.perm.school9.gate.repo.mongodb.SubmitRepository

@Service
class SubmitService {
    @Autowired
    private lateinit var submitRepository: SubmitRepository

    fun getAllSubmits(): List<Submit> {
        return submitRepository.findAll()
    }

    fun getAllSubmitsByContestAndProblemAndUser(contest: Contest, problem: Problem, user: User): List<Submit> {
        return submitRepository.findByContestIdAndProblemIdAndUserId(contest.id, problem.id, user.id)
    }

    fun getSubmitById(submitId: String): Submit {
        return submitRepository.findById(submitId).orElseThrow { SubmitNotFoundException() }
    }

    fun addSubmitByContestAndProblemAndUser(submit: Submit, contest: Contest, problem: Problem, user: User) {
        submit.apply {
            contestId = contest.id
            problemId = problem.id
            userId = user.id
        }
        submitRepository.insert(submit)
    }
}