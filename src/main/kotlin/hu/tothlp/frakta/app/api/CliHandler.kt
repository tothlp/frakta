package hu.tothlp.hu.tothlp.frakta.app.api

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import hu.tothlp.hu.tothlp.frakta.app.core.FraktaApi
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry.getBean
import hu.tothlp.hu.tothlp.frakta.app.core.interaction.InteractionHandler

class CliHandler : InteractionHandler {
	override fun start(argv: List<String>) = FraktaCli()
		.subcommands(listOf(ListCoffees())).main(argv)

	class FraktaCli : CliktCommand() {
		override fun run() = Unit
	}

	class ListCoffees(
		private val api: FraktaApi = getBean<FraktaApi>()
	) : CliktCommand() {
		override fun run() {
			val coffees = api.listCoffees()
			val t = Terminal()
			t.println(table {
				header {
					row(
						"id",
						"name",
						"roaster",
						"origin",
						"roast",
					)
				}
				body {
					coffees.forEach {
						row(it.id, it.name, it.roaster, it.origin, it.roast)
					}
				}
			})
		}
	}
}