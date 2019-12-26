package ru.perm.school9.gate.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.perm.school9.gate.model.Contest
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
        // specify exception
        return contestRepository.findById(contestId).orElseThrow { Exception() }
    }
}