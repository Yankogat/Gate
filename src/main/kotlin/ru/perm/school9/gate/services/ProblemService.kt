package ru.perm.school9.gate.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.Problem
import ru.perm.school9.gate.repo.mongodb.ProblemRepository

@Service
class ProblemService {
    @Autowired
    lateinit var problemRepository: ProblemRepository

    fun getAllProblemsFromContest(contest: Contest): List<Problem>
            = problemRepository.findAllById(contest.problemIds).toList()
}