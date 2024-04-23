package hu.tothlp.hu.tothlp.frakta.app.api.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.options.versionOption
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean
import hu.tothlp.hu.tothlp.frakta.app.core.version.VersionHandler

class FraktaCli(
	private val versionHandler: VersionHandler = getBean<VersionHandler>()
) : CliktCommand() {
	override fun run() = Unit

	init {
		versionOption(
			versionHandler.getVersion(),
			names = setOf("--version", "-v"),
			message = { "Frakta version: $it" }
		)
		subcommands(registerSubcommands())
	}

	private fun registerSubcommands() = listOf(
		ListCoffeeCommand(),
		GetCoffeeCommand(),
	)


}