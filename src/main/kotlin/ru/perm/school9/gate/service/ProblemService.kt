package ru.perm.school9.gate.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.ProblemNotFoundException
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.Problem
import ru.perm.school9.gate.repo.mongodb.ProblemRepository
import java.lang.Exception

@Service
class ProblemService {
    @Autowired
    private lateinit var problemRepository: ProblemRepository

    fun getAllProblemsFromContest(contest: Contest): List<Problem> = problemRepository.findAllById(contest.problemIds).toList()

    fun getProblemById(problemId: String): Problem {
        return problemRepository.findById(problemId).orElseThrow { ProblemNotFoundException() }
    }

    fun getProblemFromContestById(contest: Contest, problemId: String): Problem {
        if (contest.problemIds.contains(problemId))
            return getProblemById(problemId)
        else
            throw ProblemNotFoundException()
    }
}