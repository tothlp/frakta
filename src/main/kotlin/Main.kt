package hu.tothlp

import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean

/**
 * Entry point of the application.
 * The running of the application is delegated to injected [InteractionHandler].
 * The [BeanRegistry] should be initialized, but BeanRegistry.init will be called automatically, when the first bean is requested with [BeanRegistry.getBean].
 */
fun main(args: Array<String>) {
	getBean<InteractionHandler>().start(args.toList())
}