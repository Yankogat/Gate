package ru.perm.school9.gate.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import ru.perm.school9.gate.service.UserService

@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Autowired
    private lateinit var userService: UserService

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin().loginPage("/login").permitAll()
    }


    public override fun configure(builder: AuthenticationManagerBuilder) {
        builder.userDetailsService(userService)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}