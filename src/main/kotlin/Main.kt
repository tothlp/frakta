package hu.tothlp

import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean

class Frakta(
	private val coffeeRepository: CoffeeRepository = getBean<CoffeeRepository>(),
	private val inputHandler: InteractionHandler = getBean<InteractionHandler>(),
) {
	/**
	 * Runs all necessary initialization, then delegates running to the [InteractionHandler].
	 */
	fun run(args: Array<String>) {
		init()
		inputHandler.start(args.toList())
	}

	private fun init() {
		coffeeRepository.init()
	}

}

/**
 * Entry point of the application.
 * The running of the application is delegated to the [Frakta] class. Naturally, the [BeanRegistry] need to be initialized before running the application.
 */
fun main(args: Array<String>) {
	try {
	BeanRegistry.init()
	Frakta().run(args)
	} catch (e: Exception) {
		println("An error occurred: ${e.message}")
		e.printStackTrace()
	}
}