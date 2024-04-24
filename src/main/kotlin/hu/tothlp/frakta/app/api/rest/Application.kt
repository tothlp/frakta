package hu.tothlp.frakta.app.api.rest

import hu.tothlp.frakta.app.api.rest.plugins.configureRouting
import hu.tothlp.hu.tothlp.frakta.app.api.rest.plugins.configureSerialization
import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


class RestHandler(
) : InteractionHandler {

    override fun start(argv: List<String>) {
        embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
    }

}

fun Application.module() {
    configureRouting()
    configureSerialization()
}