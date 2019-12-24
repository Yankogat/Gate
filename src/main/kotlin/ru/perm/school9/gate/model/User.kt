package ru.perm.school9.gate.model

import ru.perm.school9.gate.model.groups.ContestGroup
import ru.perm.school9.gate.model.groups.SubmitGroup

class User : SubmitGroup, ContestGroup {
    override var submits: List<Submit> = listOf()
    override var contests: List<Contest> = listOf()
}