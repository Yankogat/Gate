package ru.perm.school9.gate.model.groups

import ru.perm.school9.gate.model.User

interface UserGroup {
    var users: List<User>
}