package ru.perm.school9.gate.model

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.perm.school9.gate.exception.BadRegistrationDataException
import ru.perm.school9.gate.model.enum.EUserRole

//TODO
// add participations
@Document(collection = "Users")
class User : UserDetails {
    @Id
    var id: String? = null
    var loginInfo: UserLoginInfo? = null
    var email: String? = null
    var role: EUserRole? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority>
            = arrayListOf(SimpleGrantedAuthority(role?.name ?: EUserRole.USER.name))
    @Transient
    override fun getUsername(): String = this.loginInfo?.username ?: ""
    @Transient
    override fun getPassword(): String = this.loginInfo?.password ?: ""


    //TODO
    // maybe implement these later
    @Transient
    override fun isEnabled() = true
    @Transient
    override fun isAccountNonLocked() = true
    @Transient
    override fun isAccountNonExpired() = true
    @Transient
    override fun isCredentialsNonExpired() = true

    // "prepare" part of the function consists of resetting variables that may be explicitly set by user eg role and id
    fun validateAndPrepareForRegistration() {
        // check if username, password and email are not null
        if (this.loginInfo == null || this.loginInfo!!.password == null || this.loginInfo!!.username == null || this.email == null)
            throw BadRegistrationDataException()


        this.role = EUserRole.USER
        this.id = null
        return


    }

}