package ru.perm.school9.gate.service

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Service
import ru.perm.school9.gate.exception.TesterCommunicationException
import ru.perm.school9.gate.model.Submit
import java.net.URL
import javax.annotation.PostConstruct

@Service
@ConfigurationProperties("tester")
class TesterCommunicationService {
    @Value("\${host}")
    private lateinit var host: String

    @Value("\${port}")
    private lateinit var port: String

    @Throws(TesterCommunicationException::class)
    fun sendSubmitForTesting(submit: Submit) {
        //TODO
        val response = Fuel.post("").body(submit.id!!).response()
        println("Request to test submit ${submit.id!!} has been set.\n Tester has responded with: $response.")
    }

    @PostConstruct
    fun configure() {
        FuelManager.instance.basePath = "http://$host:$port/"
    }
}