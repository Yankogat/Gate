package ru.perm.school9.gate.repo.mongodb

import org.springframework.data.mongodb.repository.MongoRepository
import ru.perm.school9.gate.model.Problem

interface ProblemRepository : MongoRepository<Problem, String>