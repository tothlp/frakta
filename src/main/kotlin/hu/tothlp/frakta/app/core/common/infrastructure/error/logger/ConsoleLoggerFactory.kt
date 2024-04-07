package hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger

class ConsoleLoggerFactory: LoggerFactory {
	private val loggers = mutableMapOf<Class<*>, Logger>()

	override fun getLogger(forClass: Class<*>): Logger {
		return loggers.getOrPut(forClass) { ConsoleLogger(forClass) }
	}
}