package hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.logger

interface LoggerFactory {
	fun getLogger(forClass: Class<*>): Logger
}