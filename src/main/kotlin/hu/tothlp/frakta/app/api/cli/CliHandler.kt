package hu.tothlp.hu.tothlp.frakta.app.api.cli

import com.github.ajalt.mordant.terminal.Terminal
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean
import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler

class CliHandler(
	private val terminal: Terminal = getBean<Terminal>()
) : InteractionHandler {

	override fun start(argv: List<String>) = try {
		FraktaCli().main(argv)
	} catch (e: Exception) {
		exceptionHandler(e)
	}

	private fun exceptionHandler(e: Exception) {
		terminal.danger("Error: ${e.message}")
	}

}