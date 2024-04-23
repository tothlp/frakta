package hu.tothlp.hu.tothlp.frakta.app.api.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.prompt
import com.github.ajalt.clikt.parameters.types.long
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import hu.tothlp.hu.tothlp.frakta.app.api.cli.Errors.fail
import hu.tothlp.hu.tothlp.frakta.app.core.coffee.CoffeeService
import hu.tothlp.hu.tothlp.frakta.app.core.di.BeanRegistry


class GetCoffeeCommand(
	private val coffeeService: CoffeeService = BeanRegistry.getBean<CoffeeService>(),
	private val terminal: Terminal = BeanRegistry.getBean<Terminal>()
) : CliktCommand(name = "show", help = "Show a coffee by id") {
	private val coffeeId: Long by option("-id", help = "Coffee ID").long().prompt()
	override fun run() {
		val coffee = coffeeService.getCoffee(coffeeId) ?: fail("Coffee not found")
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
				row(coffee.id, coffee.name, coffee.roaster, coffee.origin, coffee.roast)
			}
		})
	}
}