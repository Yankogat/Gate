package ru.perm.school9.gate.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.dto.ContestDTO
import ru.perm.school9.gate.repo.mongodb.ContestRepository

/** ContestService is responsible for constructing/deconstructing Contest objects from/to ContestDTOs
 *  and basic contest related tasks as middleman between end-user and repository
 * */
@Service
class ContestService : DTOConsumingFactory<Contest, ContestDTO> {
    @Autowired
    lateinit var contestRepository: ContestRepository

    @Autowired
    lateinit var problemService: ProblemService

    override fun constructFromDTO(dto: ContestDTO): Contest {
        // TODO
        return produce().apply {
            problems = problemService.getAllProblemsFromContestDTO(dto)
        }
    }

    override fun produce(): Contest {
        // TODO
        return Contest()
    }

    fun getAvailableContests(): List<Contest> {
        // TODO
        // Get all ContestDTOs from Repository
        val contestDTOList = contestRepository.findAll()
        // construct Contest objects using DTOs
        return contestDTOList.map { constructFromDTO(it) }
    }
}