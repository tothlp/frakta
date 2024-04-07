package hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error

open class ServerError(message: String) : Exception(message)
class NotFoundError(message: String) : ServerError(message)

fun failServerError(message: String): Nothing = throw ServerError(message)
fun failNotFoundError(message: String): Nothing = throw NotFoundError(message)