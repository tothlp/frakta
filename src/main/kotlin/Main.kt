package hu.tothlp

import hu.tothlp.hu.tothlp.frakta.app.api.FraktaApi
import hu.tothlp.hu.tothlp.frakta.app.common.logger.Logger
import hu.tothlp.hu.tothlp.frakta.app.common.logger.LoggerFactory
import hu.tothlp.hu.tothlp.frakta.app.di.Beans
import hu.tothlp.hu.tothlp.frakta.app.di.Beans.getBean

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