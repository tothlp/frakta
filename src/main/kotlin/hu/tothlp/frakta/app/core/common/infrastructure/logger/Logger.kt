package hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.logger

interface Logger {
	fun debug(message: String)
	fun info(message: String)
	fun warn(message: String)
	fun error(message: String)
}