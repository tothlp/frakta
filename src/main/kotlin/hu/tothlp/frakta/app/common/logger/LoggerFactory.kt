package hu.tothlp.hu.tothlp.frakta.app.common.logger

interface LoggerFactory {
	fun getLogger(forClass: Class<*>): Logger
}