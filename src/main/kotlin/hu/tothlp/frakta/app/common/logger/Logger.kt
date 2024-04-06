package hu.tothlp.hu.tothlp.frakta.app.common.logger

interface Logger {
	fun debug(message: String)
	fun info(message: String)
	fun warn(message: String)
	fun error(message: String)
}