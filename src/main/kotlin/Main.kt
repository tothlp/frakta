package hu.tothlp

import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean

class Frakta(
	private val inputHandler: InteractionHandler = getBean<InteractionHandler>(),
) {
	/**
	 * Runs all necessary initialization, then delegates running to the [InteractionHandler].
	 */
	fun run(args: Array<String>) {
		inputHandler.start(args.toList())
	}

}

/**
 * Entry point of the application.
 * The running of the application is delegated to the [Frakta] class.
 * The [BeanRegistry] should be initialized before calling [Frakta.run], but BeanRegistry.init will be called automatically, when the first bean is requested with [BeanRegistry.getBean].
 */
fun main(args: Array<String>) {
	try {
		Frakta().run(args)
	} catch (e: Exception) {
		println("An error occurred: ${e.message}")
		e.printStackTrace()
	}
}