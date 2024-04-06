package hu.tothlp.hu.tothlp.frakta.app.common.logger

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsoleLogger(private val forClass: Class<*>): Logger {
	override fun debug(message: String) {
		log("DEBUG", message)
	}

	override fun info(message: String) {
		log("INFO", message)
	}

	override fun warn(message: String) {
		log("WARN", message)
	}

	override fun error(message: String) {
		log("ERROR", message)
	}

	private fun log(level: String, message: String) {
		val now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
		println("$now $level [${forClass.simpleName}]: $message")
	}
}