package hu.tothlp

import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeRepository
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.useRest

/**
 * Entry point of the application.
 * The running of the application is delegated to injected [InteractionHandler].
 * The [BeanRegistry] should be initialized, but BeanRegistry.init will be called automatically, when the first bean is requested with [BeanRegistry.getBean].
 */
fun main(args: Array<String>) {
	useRest = args.contains("--rest")
	// If no arguments are provided, or only help arguments are provided, print the help message.
	if(args.isEmpty() || args.all { listOf("-h", "--help").contains(it) }) printModeHelp()

	getBean<InteractionHandler>().start(args.toList())
}

fun printModeHelp() {
	println("Modes:")
	println("  --rest         start the application as a REST API server")
	println()
}