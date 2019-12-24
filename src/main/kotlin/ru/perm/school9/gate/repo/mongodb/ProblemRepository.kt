package ru.perm.school9.gate.repo.mongodb

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import ru.perm.school9.gate.model.dto.ProblemDTO

interface ProblemRepository : MongoRepository<ProblemDTO, String>