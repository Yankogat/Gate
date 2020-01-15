package ru.perm.school9.gate.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.perm.school9.gate.model.User
import ru.perm.school9.gate.model.enum.EUserRole

@Service
class AuthenticationService : SecurityContextHolder() {
    fun getAuthenticatedUser(): User = getContext().authentication.principal as User

    fun isAdmin(): Boolean = getAuthenticatedUser().role == EUserRole.ADMIN

    fun isRoot(): Boolean = getAuthenticatedUser().role == EUserRole.ROOT
}
