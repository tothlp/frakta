package hu.tothlp.frakta.app.api.rest.plugins

import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeService
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean
import hu.tothlp.hu.tothlp.frakta.app.core.version.VersionHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        baseRoutes()
        coffeeRoutes()
    }
}

fun Route.baseRoutes() {
    val versionHandler: VersionHandler = getBean<VersionHandler>()
    route("/api") {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/version") {
            call.respondText(contentType = ContentType.Application.Json, text = versionHandler.getVersion(), status = HttpStatusCode.OK)
        }
    }
}

fun Route.coffeeRoutes() {
    val coffeeService: CoffeeService = getBean<CoffeeService>()
    route("/api/coffee") {

        get {
            val coffee = coffeeService.listCoffees()
            call.respond(coffee)
        }

        get("{id?}") {
            val id = call.parameters["id"]?.toLong() ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val coffee =
                coffeeService.getCoffee(id) ?: return@get call.respondText(
                    "No coffee with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(coffee)
        }
    }
}