package ru.perm.school9.gate.model

import ru.perm.school9.gate.model.groups.ProblemGroup
import ru.perm.school9.gate.model.groups.SubmitGroup
import ru.perm.school9.gate.model.groups.UserGroup

class Contest : ProblemGroup, UserGroup, SubmitGroup {
    override var problems: List<Problem> = listOf()
    override var users: List<User> = listOf()
    override var submits: List<Submit> = listOf()
}