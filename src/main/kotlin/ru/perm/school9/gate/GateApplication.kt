package ru.perm.school9.gate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
class GateApplication

fun main(args: Array<String>) {
	runApplication<GateApplication>(*args)
}
