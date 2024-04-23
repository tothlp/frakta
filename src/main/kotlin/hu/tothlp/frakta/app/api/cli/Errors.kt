package hu.tothlp.hu.tothlp.frakta.app.api.cli

object Errors{
	fun fail(message: String): Nothing = throw Exception(message)
}