package ru.perm.school9.gate.service

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.perm.school9.gate.model.Submit
import javax.annotation.PostConstruct

@Service
class TesterCommunicationService {
    @Value("\${tester.host}")
    private lateinit var host: String

    @Value("\${tester.port}")
    private lateinit var port: String

    fun sendSubmitForTesting(submit: Submit) {
        //TODO
        // decide if there is any authentication needed
        val response = Fuel.post("").body(submit.id!!).response()
        println("Request to test submit ${submit.id!!} has been sent.\n Tester response was: $response.")
    }

    @PostConstruct
    fun configure() {
        FuelManager.instance.basePath = "http://$host:$port/"
    }
}