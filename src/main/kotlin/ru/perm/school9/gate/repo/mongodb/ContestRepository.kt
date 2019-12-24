package ru.perm.school9.gate.repo.mongodb

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.perm.school9.gate.model.Contest
import ru.perm.school9.gate.model.dto.ContestDTO

@Repository
interface ContestRepository : MongoRepository<ContestDTO, String>