package hu.tothlp.hu.tothlp.frakta.app.api.rest

import com.sun.net.httpserver.BasicAuthenticator
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler
import java.net.InetSocketAddress
import java.net.URLDecoder


class RestHandler: InteractionHandler {
	override fun start(argv: List<String>) {
		startServer()
	}
}

fun startServer() {
	val serverPort = 8080
	val server = HttpServer.create(InetSocketAddress(serverPort), 0)
	val context = server.createContext("/api/hello", (HttpHandler { exchange: HttpExchange ->
		if ("GET" == exchange.requestMethod) {


			val name = splitQuery(exchange.requestURI.rawQuery)["name"]?.firstOrNull() ?: "Anonymous"
			val respText = "Hello $name!"
			exchange.sendResponseHeaders(200, respText.toByteArray().size.toLong())
			exchange.responseBody.use { it.write(respText.toByteArray()) }

		} else {
			exchange.sendResponseHeaders(405, -1) // 405 Method Not Allowed
		}
		exchange.close()
	}))
	context.setAuthenticator(object : BasicAuthenticator("myrealm") {
		override fun checkCredentials(user: String, pwd: String): Boolean {
			return user == "admin" && pwd == "admin"
		}
	})
	server.executor = null // creates a default executor
	server.start()
}

fun splitQuery(query: String?): Map<String, List<String>> {
	if (query.isNullOrEmpty()) {
		return emptyMap()
	}

	return query.split("&").map { it.split("=", limit = 2) }
		.groupBy({ URLDecoder.decode(it[0], "UTF-8") }, { URLDecoder.decode(it[1], "UTF-8") })
}


