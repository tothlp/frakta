package hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger

interface LoggerFactory {
	fun getLogger(forClass: Class<*>): Logger
}