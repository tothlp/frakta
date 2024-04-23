package hu.tothlp.hu.tothlp.frakta.app.api.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeService
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry

class ListCoffeeCommand(
	private val coffeeService: CoffeeService = BeanRegistry.getBean<CoffeeService>(),
	private val terminal: Terminal = BeanRegistry.getBean<Terminal>()
) : CliktCommand(name = "list", help = "List all coffees") {
	override fun run() {
		val coffees = coffeeService.listCoffees()
		terminal.println(table {
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