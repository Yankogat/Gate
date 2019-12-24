package ru.perm.school9.gate.model.groups

import ru.perm.school9.gate.model.Contest

interface ContestGroup {
    var contests: List<Contest>
}