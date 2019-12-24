package ru.perm.school9.gate.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.model.Problem
import ru.perm.school9.gate.model.dto.ContestDTO
import ru.perm.school9.gate.model.dto.ProblemDTO
import ru.perm.school9.gate.repo.mongodb.ProblemRepository

@Service
class ProblemService : DTOConsumingFactory<Problem, ProblemDTO> {
    @Autowired
    lateinit var problemRepository: ProblemRepository

    override fun constructFromDTO(dto: ProblemDTO): Problem {
        // TODO
        return Problem()
    }

    override fun produce(): Problem {
        // TODO
        return Problem()
    }

    fun getAllProblemsFromContestDTO(contestDTO: ContestDTO): List<Problem>
            = problemRepository.findAllById(contestDTO.problemsIds).map { constructFromDTO(it) }
}