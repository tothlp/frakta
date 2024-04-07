package hu.tothlp

import hu.tothlp.hu.tothlp.frakta.app.core.FraktaApi
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger.Logger
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger.LoggerFactory
import hu.tothlp.hu.tothlp.frakta.app.core.di.Beans
import hu.tothlp.hu.tothlp.frakta.app.core.di.Beans.getBean

class Frakta(
	private val api: FraktaApi = getBean<FraktaApi>(),
	private val logger: Logger = getBean<LoggerFactory>().getLogger(Frakta::class.java)
) {
	fun run() {
		init()
		hello()
	}

	private fun init() {
		logger.info("Version: ${api.getVersion()}")

	}
	private fun hello() {
		logger.info("Hello, Frakta!")
	}

}

fun main() {
	Beans.init()
	val app = Frakta()
	app.run()
}