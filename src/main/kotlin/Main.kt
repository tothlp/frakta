package hu.tothlp

import hu.tothlp.hu.tothlp.frakta.app.core.FraktaApi
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger.Logger
import hu.tothlp.hu.tothlp.frakta.app.core.common.infrastructure.error.logger.LoggerFactory
import hu.tothlp.hu.tothlp.frakta.app.core.di.Beans
import hu.tothlp.hu.tothlp.frakta.app.core.di.Beans.getBean

class Frakta(
	private val api: FraktaApi = getBean<FraktaApi>(),
	private val coffeeRepository: CoffeeRepository = getBean<CoffeeRepository>(),
	private val fraktaApi: FraktaApi = getBean<FraktaApi>(),
	private val logger: Logger = getBean<LoggerFactory>().getLogger(Frakta::class.java)
) {
	fun run(args: Array<String>) {
		init()
		fraktaApi.start(args.toList())
	}

	private fun init() {
		logger.info("Version: ${api.getVersion()}")
		coffeeRepository.init()
	}

}

fun main(args: Array<String>) {
	Beans.init()
	val app = Frakta()
	app.run(args)
}