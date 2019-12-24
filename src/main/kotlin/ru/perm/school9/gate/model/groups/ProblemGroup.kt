package ru.perm.school9.gate.model.groups

import ru.perm.school9.gate.model.Problem


interface ProblemGroup {
    var problems: List<Problem>
}