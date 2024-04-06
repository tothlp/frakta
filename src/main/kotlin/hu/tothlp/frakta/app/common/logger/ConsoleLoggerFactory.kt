package hu.tothlp.hu.tothlp.frakta.app.common.logger

class ConsoleLoggerFactory: LoggerFactory {
	private val loggers = mutableMapOf<Class<*>, Logger>()

	override fun getLogger(forClass: Class<*>): Logger {
		return loggers.getOrPut(forClass) { ConsoleLogger(forClass) }
	}
}