package hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error

open class ServerError(message: String) : Exception(message)

fun failError(message: String): Nothing = throw ServerError(message)